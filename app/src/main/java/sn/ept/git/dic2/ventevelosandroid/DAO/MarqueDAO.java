package sn.ept.git.dic2.ventevelosandroid.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import sn.ept.git.dic2.ventevelosandroid.models.Marque;

import java.util.ArrayList;
import java.util.List;

public class MarqueDAO {
    private SQLiteDatabase database;

    public MarqueDAO(SQLiteDatabase database) {
        this.database = database;
    }

    public long addMarque(Marque marque) {
        ContentValues values = new ContentValues();
        values.put("nom", marque.getNom());
        return database.insert("Marque", null, values);
    }

    public Marque getMarqueById(long id) {
        Cursor cursor = database.query("Marque", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Marque marque = cursorToMarque(cursor);
            cursor.close();
            return marque;
        }
        return null;
    }

    public List<Marque> getAllMarques() {
        List<Marque> marques = new ArrayList<>();
        Cursor cursor = database.query("Marque", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Marque marque = cursorToMarque(cursor);
                marques.add(marque);
            }
            cursor.close();
        }

        return marques;
    }

    public int updateMarque(Marque marque) {
        ContentValues values = new ContentValues();
        values.put("nom", marque.getNom());

        return database.update("Marque", values, "id = ?", new String[]{String.valueOf(marque.getId())});
    }

    public void deleteMarque(long id) throws SQLException {
        int result = database.delete("Marque", "id = ?", new String[]{String.valueOf(id)});
        if (result == 0) {
            throw new SQLException("Failed to delete Marque with ID: " + id);
        }
    }

    private Marque cursorToMarque(Cursor cursor) {
        Marque marque = new Marque();
        marque.setId(cursor.getLong(cursor.getColumnIndex("id")));
        marque.setNom(cursor.getString(cursor.getColumnIndex("nom")));
        return marque;
    }
}
