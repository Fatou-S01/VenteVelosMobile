package sn.ept.git.dic2.ventevelosandroid.methods;

import retrofit2.Call;
import retrofit2.http.GET;
import sn.ept.git.dic2.ventevelosandroid.models.Categorie;

import java.util.ArrayList;

public interface CategorieMethods {
    @GET("VenteVelos-1.0-SNAPSHOT/api/categories/")
    Call<ArrayList<Categorie>> getAllCategories();
}
