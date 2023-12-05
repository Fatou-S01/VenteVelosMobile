package sn.ept.git.dic2.ventevelosandroid.utils;

import sn.ept.git.dic2.ventevelosandroid.entites.ArticleCommande;
import sn.ept.git.dic2.ventevelosandroid.entites.Categorie;
import sn.ept.git.dic2.ventevelosandroid.entites.Client;
import sn.ept.git.dic2.ventevelosandroid.entites.Commande;
import sn.ept.git.dic2.ventevelosandroid.entites.Employe;
import sn.ept.git.dic2.ventevelosandroid.entites.Localisation;
import sn.ept.git.dic2.ventevelosandroid.entites.Magasin;
import sn.ept.git.dic2.ventevelosandroid.entites.Marque;
import sn.ept.git.dic2.ventevelosandroid.entites.Produit;
import sn.ept.git.dic2.ventevelosandroid.entites.Reponse;
import sn.ept.git.dic2.ventevelosandroid.entites.Stock;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    // Articles
    @Headers({
            "Accept: application/json"
    })
    @GET("articles_commandes")
    Call<List<ArticleCommande>> getArticles();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("articles_commandes")
    Call<ArticleCommande> addOrUpdateArticle(@Body ArticleCommande articleCommande);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("articles_commandes/{ligne}/{numero}")
    Call<Reponse> deleteArticle(@Path("ligne") Integer numero, @Path("numero") Integer ligne);

    // Categories
    @Headers({
            "Accept: application/json"
    })
    @GET("categories")
    Call<List<Categorie>> getCategories();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("categories")
    Call<Categorie> addOrUpdateCategorie(@Body Categorie categorie);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("categories/{id}")
    Call<Reponse> deleteCategorie(@Path("id") Integer id);

    //Clients
    @Headers({
            "Accept: application/json"
    })
    @GET("clients")
    Call<List<Client>> getClients();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("clients")
    Call<Client> addOrUpdateClient(@Body Client client);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("clients/{id}")
    Call<Reponse> deleteClient(@Path("id") Integer id);

    // Commandes
    @Headers({
            "Accept: application/json"
    })
    @GET("commandes")
    Call<List<Commande>> getCommandes();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("commandes")
    Call<Commande> addOrUpdateCommande(@Body Commande commande);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("commandes/{numero}")
    Call<Reponse> deleteCommande(@Path("numero") Integer numero);

    //Employes
    @Headers({
            "Accept: application/json"
    })
    @GET("employee")
    Call<List<Employe>> getEmployes();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("employee")
    Call<Employe> addOrUpdateEmploye(@Body Employe employe);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("employee/{id}")
    Call<Reponse> deleteEmploye(@Path("id") Integer id);

    //Magasins
    @Headers({
            "Accept: application/json"
    })
    @GET("magasins")
    Call<List<Magasin>> getMagasins();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("magasins")
    Call<Magasin> addOrUpdateMagasin(@Body Magasin magasin);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("magasins/{id}")
    Call<Reponse> deleteMagasin(@Path("id") Integer id);

    //Marques
    @Headers({
            "Accept: application/json"
    })
    @GET("marques")
    Call<List<Marque>> getMarques();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("marques")
    Call<Marque> addOrUpdateMarque(@Body Marque marque);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("marques/{id}")
    Call<Reponse> deleteMarque(@Path("id") Integer id);

    //Produits
    @Headers({
            "Accept: application/json"
    })
    @GET("produits")
    Call<List<Produit>> getProduits();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("produits")
    Call<Produit> addOrUpdateProduit(@Body Produit produit);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("produits/{id}")
    Call<Reponse> deleteProduit(@Path("id") Integer id);

    //Stocks
    @Headers({
            "Accept: application/json"
    })
    @GET("stock")
    Call<List<Stock>> getStocks();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("stocks")
    Call<Stock> addOrUpdateStock(@Body Stock stock);

    @Headers({
            "Accept: application/json"
    })
    @DELETE("stocks/{magasin_id}/{produit_id}")
    Call<Reponse> deleteStock(@Path("magasin_id") Integer magasin_id, @Path("produit_id") Integer produit_id);

    // Localisation
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("localisation")
    Call<Localisation> addOrUpdateLocalisation(@Body Localisation localisation);
}
