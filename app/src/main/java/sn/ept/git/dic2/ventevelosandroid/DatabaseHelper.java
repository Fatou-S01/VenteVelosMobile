package sn.ept.git.dic2.ventevelosandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import sn.ept.git.dic2.ventevelosandroid.models.Marque;
import sn.ept.git.dic2.ventevelosandroid.models.Categorie;
import sn.ept.git.dic2.ventevelosandroid.models.Magasin;
import sn.ept.git.dic2.ventevelosandroid.models.Produit;
import sn.ept.git.dic2.ventevelosandroid.models.Stock;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "stock_db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create necessary tables
        db.execSQL(createTableCategorie());
        db.execSQL(createTableMarque());
        db.execSQL(createTableMagasin());
        db.execSQL(createTableProduit());
        db.execSQL(createTableStock());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    private String createTableMarque() {
        return "CREATE TABLE " + Marque.class.getSimpleName() + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT)";
    }

    private String createTableCategorie() {
        return "CREATE TABLE " + Categorie.class.getSimpleName() + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT)";
    }

    private String createTableMagasin() {
        return "CREATE TABLE " + Magasin.class.getSimpleName() + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "telephone TEXT," +
                "email TEXT," +
                "adresse TEXT," +
                "ville TEXT," +
                "etat TEXT," +
                "code_zip TEXT)";
    }

    private String createTableProduit() {
        return "CREATE TABLE " + Produit.class.getSimpleName() + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "categorie_id INTEGER," +
                "marque_id INTEGER," +
                "annee_model INTEGER," +
                "prix_depart REAL," +
                "FOREIGN KEY (categorie_id) REFERENCES Categorie(id)," +
                "FOREIGN KEY (marque_id) REFERENCES Marque(id))";
    }

    private String createTableStock() {
        return "CREATE TABLE " + Stock.class.getSimpleName() + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "magasin_id INTEGER," +
                "produit_id INTEGER," +
                "quantite INTEGER," +
                "FOREIGN KEY (magasin_id) REFERENCES " + Magasin.class.getSimpleName() + "(id)," +
                "FOREIGN KEY (produit_id) REFERENCES " + Produit.class.getSimpleName() + "(id))";
    }
}
