package sn.ept.git.dic2.ventevelosandroid.activities.employe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import sn.ept.git.dic2.ventevelosandroid.R;
import sn.ept.git.dic2.ventevelosandroid.activities.HomeActivity;
import sn.ept.git.dic2.ventevelosandroid.entites.Employe;
import sn.ept.git.dic2.ventevelosandroid.entites.Reponse;
import sn.ept.git.dic2.ventevelosandroid.services.ApiService;
import sn.ept.git.dic2.ventevelosandroid.utils.ApiInterface;
import sn.ept.git.dic2.ventevelosandroid.utils.RetrofitClient;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEmployes extends AppCompatActivity {

    List<Employe> employeList;
    BroadcastReceiver broadcastReceiver;
    ListView listView;
    ApiInterface apiInterface;
    Button btn1;
    ImageButton btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employes);

        listView = findViewById(R.id.listView);
        btn1 = findViewById(R.id.addButton);
        btn2 = findViewById(R.id.backButton);
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent != null && "ACTION_EMPLOYES_LOADED".equals(intent.getAction())) {
                    List<Employe> data = (List<Employe>) intent.getSerializableExtra("employeList");
                    if (data != null) {
                        employeList = data;
                        ArrayAdapter<Employe> adapter = new ArrayAdapter<Employe>(ListEmployes.this, R.layout.list_tile_layout, R.id.title, employeList) {
                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);
                                if (view == null) {
                                    LayoutInflater inflater = LayoutInflater.from(getContext());
                                    view = inflater.inflate(R.layout.list_tile_layout, parent, false);
                                }

                                Employe item = getItem(position);

                                if (item != null) {
                                    TextView elementNameTextView = view.findViewById(R.id.title);
                                    TextView detailsTextView = view.findViewById(R.id.details);
                                    ImageView trashcanIcon = view.findViewById(R.id.trashcanIcon);

                                    elementNameTextView.setText(item.getPrenom()+" "+item.getNom());
                                    detailsTextView.setText("Magasin: " + item.getMagasinId().getNom());

                                    view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(ListEmployes.this, DetailsEmployes.class);
                                            intent.putExtra("selectedEmploye", item);
                                            startActivity(intent);
                                        }
                                    });

                                    trashcanIcon.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            deleteEmploye(item.getId());
                                        }
                                    });
                                }

                                return view;
                            }
                        };
                        listView.setAdapter(adapter);
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter("ACTION_EMPLOYES_LOADED");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter);

        getEmployes();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListEmployes.this, AddEmploye.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListEmployes.this, HomeActivity.class));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
    }

    private void getEmployes() {
        Intent serviceIntent = new Intent(this, ApiService.class);
        serviceIntent.setAction("getEmployes");
        startService(serviceIntent);
    }

    private void deleteEmploye(Integer id) {
        Call<Reponse> call = apiInterface.deleteEmploye(id);
        call.enqueue(
                new Callback<Reponse>() {
                    @Override
                    public void onResponse(Call<Reponse> call, Response<Reponse> response) {
                        if (response.isSuccessful()) {
                            getEmployes();
                            Toast.makeText(ListEmployes.this, "L'employé a été supprimé et les données rechargées", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ListEmployes.this, "La récupération des données a échoué", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Reponse> call, Throwable t) {
                        Log.d("ListCommandes", "Error "+t);
                    }
                }
        );
    }
}