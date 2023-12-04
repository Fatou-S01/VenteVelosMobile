package sn.ept.git.dic2.ventevelosandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import sn.ept.git.dic2.ventevelosandroid.R;
import sn.ept.git.dic2.ventevelosandroid.models.Categorie;

import java.util.ArrayList;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.CategorieViewHolder> {

    private ArrayList<Categorie> categories;
    private Context context;

    public CategorieAdapter(Context context) {
        this.context = context;
        this.categories = new ArrayList<>();
    }

    public void addItems(ArrayList<Categorie> newCategories) {
        categories.addAll(newCategories);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategorieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorie, parent, false);
        return new CategorieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorieViewHolder holder, int position) {
        Categorie categorie = categories.get(position);
        holder.bind(categorie);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategorieViewHolder extends RecyclerView.ViewHolder {
        private TextView numeroTextView;
        private TextView nomTextView;

        CategorieViewHolder(@NonNull View itemView) {
            super(itemView);
            numeroTextView = itemView.findViewById(R.id.numeroTextView);
            nomTextView = itemView.findViewById(R.id.nomCategorieTextView);
        }

        void bind(Categorie categorie) {
            numeroTextView.setText("Numéro: " + categorie.getId());
            nomTextView.setText("Nom Categorie: " + categorie.getNom());
        }
    }
}
