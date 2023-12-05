package sn.ept.git.dic2.ventevelosandroid.activities.client;

import androidx.appcompat.app.AppCompatActivity;
import sn.ept.git.dic2.ventevelosandroid.R;
import sn.ept.git.dic2.ventevelosandroid.entites.Adresse;
import sn.ept.git.dic2.ventevelosandroid.entites.Client;
import sn.ept.git.dic2.ventevelosandroid.utils.ApiInterface;
import sn.ept.git.dic2.ventevelosandroid.utils.RetrofitClient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClient extends AppCompatActivity {

    ApiInterface apiInterface;
    EditText edNom, edPrenom, edEmail, edTel, edCode, edVille, edEtat, edAdresse;
    Button btn1;
    ImageButton btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        edNom = findViewById(R.id.nomEditText);
        edPrenom = findViewById(R.id.prenomEditText);
        edEmail = findViewById(R.id.emailEditText);
        edTel = findViewById(R.id.telephoneEditText);
        edCode = findViewById(R.id.codeEditText);
        edVille = findViewById(R.id.villeEditText);
        edEtat = findViewById(R.id.etatEditText);
        edAdresse = findViewById(R.id.adresseEditText);
        btn1 = findViewById(R.id.addButton);
        btn2 = findViewById(R.id.backButton);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom=edNom.getText().toString();
                String prenom=edPrenom.getText().toString();
                String email=edEmail.getText().toString();
                if (nom==null||prenom==null||email==null) {
                    Toast.makeText(AddClient.this, "Le nom, prenom et email sont obligatoires", Toast.LENGTH_SHORT);
                } else {
                    Client client = new Client();
                    client.setNom(nom);
                    client.setPrenom(prenom);
                    client.setEmail(email);
                    client.setTelephone(edTel.getText().toString());
                    client.setAdresse(new Adresse(edAdresse.getText().toString(),edVille.getText().toString(),edEtat.getText().toString(),edCode.getText().toString()));
                    addClient(client);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddClient.this, ListClients.class));
            }
        });
    }

    private void addClient(Client client) {
        Call<Client> call = apiInterface.addOrUpdateClient(client);
        call.enqueue(
                new Callback<Client>() {
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(AddClient.this, ListClients.class));
                        } else {
                            Toast.makeText(AddClient.this, "L'enregistrement des données a échoué", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {
                        Log.d("AddClient", "Error "+t);
                    }
                }
        );
    }
}