package sn.ept.git.dic2.ventevelosandroid.fragments;

// ListeMarquesFragment.java
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
import sn.ept.git.dic2.ventevelosandroid.adapters.MarqueAdapter;
import sn.ept.git.dic2.ventevelosandroid.R;
import sn.ept.git.dic2.ventevelosandroid.RetrofitClient;
import sn.ept.git.dic2.ventevelosandroid.methods.MarqueMethods;
import sn.ept.git.dic2.ventevelosandroid.models.Marque;

import java.util.ArrayList;

public class ListeMarquesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MarqueAdapter marqueAdapter;
    private int currentPage = 1;
    private boolean isLoading = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liste_marques, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewMarques);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        marqueAdapter = new MarqueAdapter(getContext());
        recyclerView.setAdapter(marqueAdapter);

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
                        fetchMarques();
                    }
                }
            }
        });

        // Initial data load
        fetchMarques();

        return view;
    }

    private void fetchMarques() {
        isLoading = true;

        // Use Retrofit to fetch the list of marques
        // Adjust the API call based on your actual API endpoint for marques
        MarqueMethods marqueMethods = RetrofitClient.getRetrofitInstance().create(MarqueMethods.class);
        Call<ArrayList<Marque>> call = marqueMethods.getAllMarques();

        call.enqueue(new Callback<ArrayList<Marque>>() {
            @Override
            public void onResponse(Call<ArrayList<Marque>> call, Response<ArrayList<Marque>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Marque> marques = response.body();
                    marqueAdapter.addItems(marques);
                } else {
                    // Handle error
                    // Log.e(TAG, "Error loading marques: " + response.message());
                }

                isLoading = false;
            }

            @Override
            public void onFailure(Call<ArrayList<Marque>> call, Throwable t) {
                // Handle failure
                // Log.e(TAG, "Failed to load marques: " + t.getMessage());
                isLoading = false;
            }
        });
    }
}
