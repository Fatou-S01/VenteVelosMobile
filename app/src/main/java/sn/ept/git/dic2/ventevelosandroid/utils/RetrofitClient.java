package sn.ept.git.dic2.ventevelosandroid.utils;

import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(100, TimeUnit.SECONDS); // Augmentez le délai d'attente ici
            httpClient.readTimeout(100, TimeUnit.SECONDS);
            httpClient.writeTimeout(100, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://127.0.0.1:8080/VentesVelos-1.0-SNAPSHOT/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())  // Associez le client OkHttpClient à Retrofit
                    .build();
        }
        return retrofit;
    }

}
