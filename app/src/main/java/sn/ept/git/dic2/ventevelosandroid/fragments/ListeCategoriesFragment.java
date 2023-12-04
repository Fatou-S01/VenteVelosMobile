package sn.ept.git.dic2.ventevelosandroid.fragments;
// ListeCategoriesFragment.java
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
import sn.ept.git.dic2.ventevelosandroid.adapters.CategorieAdapter;
import sn.ept.git.dic2.ventevelosandroid.R;
import sn.ept.git.dic2.ventevelosandroid.RetrofitClient;
import sn.ept.git.dic2.ventevelosandroid.methods.CategorieMethods;
import sn.ept.git.dic2.ventevelosandroid.models.Categorie;

import java.util.ArrayList;

public class ListeCategoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategorieAdapter categorieAdapter;
    private int currentPage = 1;
    private boolean isLoading = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liste_categories, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCategories);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        categorieAdapter = new CategorieAdapter(getContext());
        recyclerView.setAdapter(categorieAdapter);

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
                        fetchCategories();
                    }
                }
            }
        });

        // Initial data load
        fetchCategories();

        return view;
    }

    private void fetchCategories() {
        isLoading = true;

        // Use Retrofit to fetch the list of categories
        // Adjust the API call based on your actual API endpoint for categories
        CategorieMethods categorieMethods = RetrofitClient.getRetrofitInstance().create(CategorieMethods.class);
        Call<ArrayList<Categorie>> call = categorieMethods.getAllCategories();

        call.enqueue(new Callback<ArrayList<Categorie>>() {
            @Override
            public void onResponse(Call<ArrayList<Categorie>> call, Response<ArrayList<Categorie>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Categorie> categories = response.body();
                    categorieAdapter.addItems(categories);
                } else {
                    // Handle error
                    // Log.e(TAG, "Error loading categories: " + response.message());
                }

                isLoading = false;
            }

            @Override
            public void onFailure(Call<ArrayList<Categorie>> call, Throwable t) {
                // Handle failure
                // Log.e(TAG, "Failed to load categories: " + t.getMessage());
                isLoading = false;
            }
        });
    }
}
