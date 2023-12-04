package sn.ept.git.dic2.ventevelosandroid;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import sn.ept.git.dic2.ventevelosandroid.adapters.ProduitAdapter;
import sn.ept.git.dic2.ventevelosandroid.fragments.HomeFragment;
import sn.ept.git.dic2.ventevelosandroid.fragments.ListeCategoriesFragment;
import sn.ept.git.dic2.ventevelosandroid.fragments.ListeMarquesFragment;
import sn.ept.git.dic2.ventevelosandroid.fragments.ListeProduitsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button listeProduits;
    private RecyclerView recyclerView;
    private ProduitAdapter produitAdapter;
    private int currentPage = 1;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Load the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.navigation_liste_produits) {
                    selectedFragment = new ListeProduitsFragment();
                } else if (item.getItemId() == R.id.navigation_liste_categories) {
                    selectedFragment = new ListeCategoriesFragment();
                } else if (item.getItemId() == R.id.navigation_liste_marques) {
                    selectedFragment = new ListeMarquesFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                }

                return true;
            }
        };

}