package sn.ept.git.dic2.ventevelosandroid.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManager {

    private Context context;
    private OnConnectionCheckListener listener;

    public ConnectionManager(Context context) {
        this.context = context;
    }

    public void setOnConnectionCheckListener(OnConnectionCheckListener listener) {
        this.listener = listener;
    }



    public interface OnConnectionCheckListener {
        void onConnectionChecked(boolean isAccessible);
    }

}
