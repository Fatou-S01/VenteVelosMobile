package sn.ept.git.dic2.ventevelosandroid.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import sn.ept.git.dic2.ventevelosandroid.FormulaireActivity;
import sn.ept.git.dic2.ventevelosandroid.R;
import sn.ept.git.dic2.ventevelosandroid.models.Produit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.ProduitViewHolder> {

    private List<Produit> produits = new ArrayList<>();

    private Context context;

    public ProduitAdapter(Context context) {
        this.context = context;
    }

    public void addItems(List<Produit> newProduits) {
        produits.addAll(newProduits);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProduitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produit, parent, false);
        return new ProduitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitViewHolder holder, int position) {
        Produit produit = produits.get(position);
        holder.bind(produit);

        holder.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log the ID of the clicked produit
                Log.e("ProduitAdapter", "Clicked on Order Button for Produit ID: " + produit.getId());

                showQuantityDialog(produit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    static class ProduitViewHolder extends RecyclerView.ViewHolder {
        private TextView numeroTextView;
        private TextView nomTextView;
        private TextView marqueTextView;
        private TextView categorieTextView;
        private TextView prixDepartTextView;
        private TextView anneeModelTextView;

        private Button orderButton;

        ProduitViewHolder(@NonNull View itemView) {
            super(itemView);
            numeroTextView = itemView.findViewById(R.id.numeroTextView);
            nomTextView = itemView.findViewById(R.id.nomTextView);
            marqueTextView = itemView.findViewById(R.id.marqueTextView);
            categorieTextView = itemView.findViewById(R.id.categorieTextView);
            prixDepartTextView = itemView.findViewById(R.id.prixDepartTextView);
            anneeModelTextView = itemView.findViewById(R.id.anneeModelTextView);

            orderButton = itemView.findViewById(R.id.orderButton);
        }

        void bind(Produit produit) {
            numeroTextView.setText("Numéro: " + produit.getId());
            nomTextView.setText("Nom Produit: " + produit.getNom());
            marqueTextView.setText("Marque: " + produit.getMarque().getNom());
            categorieTextView.setText("Catégorie: " + produit.getCategorie().getNom());
            prixDepartTextView.setText("Prix départ: " + produit.getPrix_depart());
            anneeModelTextView.setText("Année Modèle: " + produit.getAnnee_model());
        }
    }

    private void showQuantityDialog(Produit produit) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Saisir la quantité");

        View viewInflated = LayoutInflater.from(context).inflate(R.layout.dialog_quantity_input, null);
        final EditText input = viewInflated.findViewById(R.id.input_quantity);
        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String quantityStr = input.getText().toString().trim();
                if (!quantityStr.isEmpty()) {
                    int quantity = Integer.parseInt(quantityStr);
                    // Faire quelque chose avec la quantité (par exemple, l'envoyer au serveur)
                    // ...
                    Log.e("ProduitAdapter", "Ordered quantity of the product (" + produit.getNom() + ") is : " + quantity);

                    // Afficher un toast ou effectuer d'autres actions nécessaires
                    Toast.makeText(context, "Vous avez commandé " + quantity + " articles de " + produit.getNom(), Toast.LENGTH_SHORT).show();


                    // Create an intent to start the FormulaireActivity
                    Intent intent = new Intent(context, FormulaireActivity.class);

                    // Set extras in the intent
                    intent.putExtra("PRODUIT_OBJECT", (Serializable) produit);
                    intent.putExtra("QUANTITE", quantity);

                    // Start the activity
                    context.startActivity(intent);

                }
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
