package sn.ept.git.dic2.ventevelosandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import sn.ept.git.dic2.ventevelosandroid.R;
import sn.ept.git.dic2.ventevelosandroid.models.Marque;

import java.util.ArrayList;

public class MarqueAdapter extends RecyclerView.Adapter<MarqueAdapter.MarqueViewHolder> {

    private ArrayList<Marque> marques;
    private Context context;

    public MarqueAdapter(Context context) {
        this.context = context;
        this.marques = new ArrayList<>();
    }

    public void addItems(ArrayList<Marque> newMarques) {
        marques.addAll(newMarques);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MarqueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marque, parent, false);
        return new MarqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarqueViewHolder holder, int position) {
        Marque marque = marques.get(position);
        holder.bind(marque);
    }

    @Override
    public int getItemCount() {
        return marques.size();
    }

    static class MarqueViewHolder extends RecyclerView.ViewHolder {
        private TextView numeroTextView;
        private TextView nomTextView;

        MarqueViewHolder(@NonNull View itemView) {
            super(itemView);
            numeroTextView = itemView.findViewById(R.id.numeroTextView);
            nomTextView = itemView.findViewById(R.id.nomMarqueTextView);
        }

        void bind(Marque marque) {
            numeroTextView.setText("Numéro: " + marque.getId());
            nomTextView.setText("Nom Marque: " + marque.getNom());
        }
    }
}
