package sn.ept.git.dic2.ventevelosandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import sn.ept.git.dic2.ventevelosandroid.models.Produit;

public class FormulaireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        // Récupérez les références des champs de saisie et du bouton Soumettre
        EditText editTextPrenom = findViewById(R.id.editTextPrenom);
        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextTelephone = findViewById(R.id.editTextTelephone);
        // Ajoutez les autres champs de saisie ici...

        Button btnSoumettre = findViewById(R.id.btnSoumettre);
        Button btnCancel = findViewById(R.id.btnCancel);

        // Récupérez l'ID du produit (vous devrez probablement le passer à cette activité)
        Produit produit = (Produit) getIntent().getSerializableExtra("PRODUIT_OBJECT");


        // Ajoutez un écouteur de clic pour le bouton Soumettre
        btnSoumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenez les valeurs saisies
                String prenom = editTextPrenom.getText().toString();
                String nom = editTextNom.getText().toString();
                String telephone = editTextTelephone.getText().toString();
                // Obtenez les autres valeurs des champs de saisie...

                // Obtenez la quantité (vous devez probablement passer cette information également)
                int quantite = getIntent().getIntExtra("QUANTITE", 0);

                // Affichez les données dans le log
                Log.e("FormulaireActivity", "ID du produit: " + produit.getId());
                Log.e("FormulaireActivity", "Quantité: " + quantite);
                Log.e("FormulaireActivity", "Prénom: " + prenom);
                Log.e("FormulaireActivity", "Nom: " + nom);
                Log.e("FormulaireActivity", "Téléphone: " + telephone);

                // Vous pouvez également utiliser ces données comme vous le souhaitez,
                // par exemple, les envoyer à un serveur, les stocker dans une base de données, etc.
            }
        });

        // Ajoutez un écouteur de clic pour le bouton Cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Annulez l'opération et retournez à l'activité principale
                finish(); // This will close the current activity and go back to the previous one
            }
        });
    }
}
