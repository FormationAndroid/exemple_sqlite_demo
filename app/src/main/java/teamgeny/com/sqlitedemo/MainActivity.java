package teamgeny.com.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = openOrCreateDatabase("ecole_db", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS eleves(nom VARCHAR, prenom VARCHAR);");

        // AJOUTER
        db.execSQL("INSERT INTO eleves VALUES('dupond', 'jean');");

        // LIRE UN CHAMP PARTICULIER
        Cursor cursor = db.query("eleves", null, " nom='dupond' ", null, null, null, null);
        if (cursor.moveToFirst()) {
            Log.d("nom : ", cursor.getString(0));
            Log.d("prenom : ", cursor.getString(1));
        }
        cursor.close();

        // TOUT LIRE
        Cursor cursor_all = db.query("eleves", null, null, null, null, null, null);
        StringBuilder buffer = new StringBuilder();
        while (cursor_all.moveToNext()) {
            buffer.append("nom : ").append(cursor_all.getString(0)).append("\n");
            buffer.append("prenom : ").append(cursor_all.getString(1)).append("\n");
        }
        Log.d("liste", buffer.toString());
        cursor_all.close();


    }
}