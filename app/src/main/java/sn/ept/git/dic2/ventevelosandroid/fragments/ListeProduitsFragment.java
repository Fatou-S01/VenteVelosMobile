package sn.ept.git.dic2.ventevelosandroid.fragments;
// ListeProduitsFragment.java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.git.dic2.ventevelosandroid.adapters.ProduitAdapter;
import sn.ept.git.dic2.ventevelosandroid.R;
import sn.ept.git.dic2.ventevelosandroid.RetrofitClient;
import sn.ept.git.dic2.ventevelosandroid.methods.ProduitMethods;
import sn.ept.git.dic2.ventevelosandroid.models.Produit;

import java.util.ArrayList;

public class ListeProduitsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProduitAdapter produitAdapter;
    private int currentPage = 1;
    private boolean isLoading = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liste_produits, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        produitAdapter = new ProduitAdapter(getContext());
        recyclerView.setAdapter(produitAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        // Load more items if reached the end of the list
                        currentPage++;
                        fetchProduits();
                    }
                }
            }
        });

        // Initial data load
        fetchProduits();

        return view;
    }

    private void fetchProduits() {
        isLoading = true;

        ProduitMethods produitMethods = RetrofitClient.getRetrofitInstance().create(ProduitMethods.class);
        Call<ArrayList<Produit>> call = produitMethods.getAllProduits();

        call.enqueue(new Callback<ArrayList<Produit>>() {
            @Override
            public void onResponse(Call<ArrayList<Produit>> call, Response<ArrayList<Produit>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Produit> produits = response.body();
                    produitAdapter.addItems(produits);
                } else {
                    // Handle error
                    // Log.e(TAG, "Error loading produits: " + response.message());
                }

                isLoading = false;
            }

            @Override
            public void onFailure(Call<ArrayList<Produit>> call, Throwable t) {
                // Handle failure
                // Log.e(TAG, "Failed to load produits: " + t.getMessage());
                isLoading = false;
            }
        });
    }
}
