package sn.ept.git.dic2.ventevelosandroid.utils;

import sn.ept.git.dic2.ventevelosandroid.entites.ArticleCommande;
import sn.ept.git.dic2.ventevelosandroid.entites.Categorie;
import sn.ept.git.dic2.ventevelosandroid.entites.Client;
import sn.ept.git.dic2.ventevelosandroid.entites.Localisation;
import sn.ept.git.dic2.ventevelosandroid.entites.Produit;
import sn.ept.git.dic2.ventevelosandroid.entites.Reponse;

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
    @DELETE("commandes/{numero}")
    Call<Reponse> deleteCommande(@Path("numero") Integer numero);


    @Headers({
            "Accept: application/json"
    })
    @DELETE("employee/{id}")
    Call<Reponse> deleteEmploye(@Path("id") Integer id);

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



    // Localisation
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("localisation")
    Call<Localisation> addOrUpdateLocalisation(@Body Localisation localisation);
}
