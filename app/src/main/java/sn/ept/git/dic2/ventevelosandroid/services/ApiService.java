package sn.ept.git.dic2.ventevelosandroid.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import sn.ept.git.dic2.ventevelosandroid.utils.ApiInterface;
import sn.ept.git.dic2.ventevelosandroid.utils.RetrofitClient;
import sn.ept.git.dic2.ventevelosandroid.entites.ArticleCommande;
import sn.ept.git.dic2.ventevelosandroid.entites.Categorie;
import sn.ept.git.dic2.ventevelosandroid.entites.Client;

import sn.ept.git.dic2.ventevelosandroid.entites.Produit;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiService extends IntentService {

    private static final String TAG = "ApiService";
    private ApiInterface apiInterface;

    public ApiService() {
        super("ApiService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (action != null ) {
                switch (action) {
                    case "getArticles":
                        getArticles();
                        break;
                    case "getCategories":
                        getCategories();
                        break;
                    case "getClients":
                        getClients();
                        break;
                    case "getProduits":
                        getProduits();
                        break;
                    default:
                        Log.d(TAG, "Action inconnue"+action);
                        break;
                }
            }
        }
    }

    private void getArticles() {
        Call<List<ArticleCommande>> call = apiInterface.getArticles();
        call.enqueue(new Callback<List<ArticleCommande>>() {
            @Override
            public void onResponse(Call<List<ArticleCommande>> call, Response<List<ArticleCommande>> response) {
                if (response.isSuccessful()) {
                    Intent broadcastIntent = new Intent("ACTION_ARTICLES_LOADED");
                    broadcastIntent.putExtra("articleCommandeList", (Serializable) response.body());
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcastIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "La récupération des données a échoué", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ArticleCommande>> call, Throwable t) {
                Log.d(TAG, "Error" + t);
            }
        });
    }

    private void getCategories() {
        Call<List<Categorie>> call = apiInterface.getCategories();
        call.enqueue(
                new Callback<List<Categorie>>() {
                    @Override
                    public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
                        if (response.isSuccessful()) {
                            Intent broadcastIntent = new Intent("ACTION_CATEGORIES_LOADED");
                            broadcastIntent.putExtra("categorieList", (Serializable) response.body());
                            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcastIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "La récupération des données a échoué", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Categorie>> call, Throwable t) {
                        Log.d(TAG, "Error " + t);
                    }
                }
        );
    }

    private void getClients() {
        Call<List<Client>> call = apiInterface.getClients();
        call.enqueue(
                new Callback<List<Client>>() {
                    @Override
                    public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                        if (response.isSuccessful()) {
                            Intent broadcastIntent = new Intent("ACTION_CLIENTS_LOADED");
                            broadcastIntent.putExtra("clientList", (Serializable) response.body());
                            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcastIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "La récupération des données a échoué", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Client>> call, Throwable t) {
                        Log.d(TAG, "Error " + t);
                    }
                }
        );
    }





    private void getProduits() {
        Call<List<Produit>> call = apiInterface.getProduits();
        call.enqueue(
                new Callback<List<Produit>>() {
                    @Override
                    public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                        if (response.isSuccessful()) {
                            Intent broadcastIntent = new Intent("ACTION_PRODUITS_LOADED");
                            broadcastIntent.putExtra("produitList", (Serializable) response.body());
                            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcastIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "La récupération des données a échoué", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Produit>> call, Throwable t) {
                        Log.d(TAG, "Error "+t);
                    }
                }
        );
    }


}