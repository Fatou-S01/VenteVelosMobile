package sn.ept.git.dic2.ventevelosandroid.activities.produit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import sn.ept.git.dic2.ventevelosandroid.R;
import sn.ept.git.dic2.ventevelosandroid.entites.Categorie;
import sn.ept.git.dic2.ventevelosandroid.entites.Produit;
import sn.ept.git.dic2.ventevelosandroid.entites.Marque;
import sn.ept.git.dic2.ventevelosandroid.services.ApiService;
import sn.ept.git.dic2.ventevelosandroid.utils.ApiInterface;
import sn.ept.git.dic2.ventevelosandroid.utils.CustomSpinnerAdapter;
import sn.ept.git.dic2.ventevelosandroid.utils.RetrofitClient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsProduits extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;
    List<Categorie> categorieList;
    List<Marque> marqueList;
    List<String> categorieStringList = new ArrayList<>();
    List<String> marqueStringList = new ArrayList<>();
    ApiInterface apiInterface;
    EditText edNom, edAnnee, edPrix;
    Button btn1;
    ImageButton btn2;
    int selectedCategoriePosition, selectedMarquePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_produits);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Produit selectedProduit = (Produit) getIntent().getSerializableExtra("selectedProduit");
        edNom = findViewById(R.id.nomEditText);
        edAnnee = findViewById(R.id.anneeEditText);
        edPrix = findViewById(R.id.prixEditText);
        btn1 = findViewById(R.id.modifyButton);
        btn2 = findViewById(R.id.backButton);

        edNom.setText(String.valueOf(selectedProduit.getNom()));
        edAnnee.setText(String.valueOf(selectedProduit.getAnneeModel()));
        edPrix.setText(String.valueOf(selectedProduit.getPrixDepart()));

         broadcastReceiver = new BroadcastReceiver() {
             @Override
             public void onReceive(Context context, Intent intent) {
                 if (intent != null && "ACTION_CATEGORIES_LOADED".equals(intent.getAction())) {
                     List<Categorie> data = (List<Categorie>) intent.getSerializableExtra("categorieList");
                     if (data != null) {
                         categorieList = data;
                         for (Categorie c: categorieList) {
                             categorieStringList.add(c.getNom());
                         }
                         Spinner categorieSpinner = findViewById(R.id.categorieSpinner);
                         CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(DetailsProduits.this, R.layout.spinner_item, categorieStringList);
                         spinnerAdapter.setDropDownViewResource(R.layout.spinner_item);
                         categorieSpinner.setAdapter(spinnerAdapter);
                         if (selectedProduit != null) {
                             selectedCategoriePosition = getCategoriePosition(selectedProduit.getCategorieId().getNom());
                             categorieSpinner.setSelection(selectedCategoriePosition);
                         }
                         categorieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                             @Override
                             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                 View selectedItem = parent.getSelectedView();
                                 if (selectedItem != null) {
                                     selectedItem.setBackgroundColor(getResources().getColor(R.color.bg_dark_subtle));
                                     String selectedCategorie = (String) parent.getItemAtPosition(position);
                                     selectedCategoriePosition= getCategoriePosition(selectedCategorie);
                                     categorieSpinner.setSelection(selectedCategoriePosition);
                                 }
                             }
                             @Override
                             public void onNothingSelected(AdapterView<?> parent) {
                             }
                         });
                     }
                 } else if (intent != null && "ACTION_MARQUES_LOADED".equals(intent.getAction())) {
                     List<Marque> data = (List<Marque>) intent.getSerializableExtra("marqueList");
                     if (data != null) {
                         marqueList = data;
                         for (Marque m: marqueList) {
                             marqueStringList.add(m.getNom());
                         }
                         Spinner marqueSpinner = findViewById(R.id.marqueSpinner);
                         CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(DetailsProduits.this, R.layout.spinner_item, marqueStringList);
                         spinnerAdapter.setDropDownViewResource(R.layout.spinner_item);
                         marqueSpinner.setAdapter(spinnerAdapter);
                         if (selectedProduit != null) {
                             selectedMarquePosition= getMarquePosition(selectedProduit.getMarqueId().getNom());
                             marqueSpinner.setSelection(selectedMarquePosition);
                         }
                         marqueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                             @Override
                             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                 View selectedItem = parent.getSelectedView();
                                 if (selectedItem != null) {
                                     selectedItem.setBackgroundColor(getResources().getColor(R.color.bg_dark_subtle));
                                     String selectedMarque = (String) parent.getItemAtPosition(position);
                                     selectedMarquePosition= getMarquePosition(selectedMarque);
                                     marqueSpinner.setSelection(selectedMarquePosition);
                                 }
                             }
                             @Override
                             public void onNothingSelected(AdapterView<?> parent) {
                             }
                         });
                     }
                 }
             }
         };

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom=edNom.getText().toString();
                Short annee=Short.parseShort(edAnnee.getText().toString());
                BigDecimal prix=new BigDecimal(edPrix.getText().toString());
                Marque marque=marqueList.get(selectedMarquePosition);
                Categorie categorie=categorieList.get(selectedCategoriePosition);
                if (nom==null||annee==null||prix==null||marque==null||categorie==null) {
                    Toast.makeText(DetailsProduits.this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT);
                } else {
                    if (selectedProduit != null) {
                        selectedProduit.setNom(nom);
                        selectedProduit.setAnneeModel(annee);
                        selectedProduit.setPrixDepart(prix);
                        selectedProduit.setMarqueId(marque);
                        selectedProduit.setCategorieId(categorie);
                        updateProduit(selectedProduit);
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsProduits.this, ListProduits.class));
            }
        });

        IntentFilter filter1 = new IntentFilter("ACTION_CATEGORIES_LOADED");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter1);
        IntentFilter filter2 = new IntentFilter("ACTION_MARQUES_LOADED");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter2);
        getCategories();
        getMarques();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
    }

    private int getMarquePosition(String s) {
        if (s != null && marqueList != null) {
            for (int i = 0; i < marqueList.size(); i++) {
                if (s.equals(marqueList.get(i).getNom())) {
                    return i;
                }
            }
        }
        return 0;
    }

    private int getCategoriePosition(String s) {
        if (s != null && categorieList != null) {
            for (int i = 0; i < categorieList.size(); i++) {
                if (s.equals(categorieList.get(i).getNom())) {
                    return i;
                }
            }
        }
        return 0;
    }

    private void getCategories() {
        Intent serviceIntent = new Intent(this, ApiService.class);
        serviceIntent.setAction("getCategories");
        startService(serviceIntent);
    }

    private void getMarques() {
        Intent serviceIntent = new Intent(this, ApiService.class);
        serviceIntent.setAction("getMarques");
        startService(serviceIntent);
    }

    private void updateProduit(Produit produit) {
        Call<Produit> call = apiInterface.addOrUpdateProduit(produit);
        call.enqueue(
                new Callback<Produit>() {
                    @Override
                    public void onResponse(Call<Produit> call, Response<Produit> response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(DetailsProduits.this, ListProduits.class));
                        } else {
                            Toast.makeText(DetailsProduits.this, "L'enregistrement des données a échoué", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Produit> call, Throwable t) {
                        Log.d("DetailsProduits", "Error "+t);
                    }
                }
        );
    }
}