package sn.ept.git.dic2.ventevelosandroid.methods;

import retrofit2.Call;
import retrofit2.http.GET;
import sn.ept.git.dic2.ventevelosandroid.models.Marque;

import java.util.ArrayList;

public interface MarqueMethods {
    @GET("VenteVelos-1.0-SNAPSHOT/api/marques/")
    Call<ArrayList<Marque>> getAllMarques();
}
