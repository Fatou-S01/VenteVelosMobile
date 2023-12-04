package sn.ept.git.dic2.ventevelosandroid.methods;

import retrofit2.Call;
import retrofit2.http.GET;
import sn.ept.git.dic2.ventevelosandroid.models.Produit;

import java.util.ArrayList;

public interface ProduitMethods {
    @GET("VenteVelos-1.0-SNAPSHOT/api/produits/")
    Call<ArrayList<Produit>> getAllProduits();
}
