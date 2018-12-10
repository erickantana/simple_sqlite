package id.co.freshmeat.simple;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListUserActivity extends AppCompatActivity {

    SqlHelper sqlHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        /* Lewati
        sqlHelper = new SqlHelper(getApplicationContext());
        db = sqlHelper.getReadableDatabase();

        String[] columns = {"username", "password"};

        Cursor cursor = db.query("user", columns, null, null, null, null, null);
        List<String> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            String username, password;
            username = cursor.getString(cursor.getColumnIndex("username"));
            password = cursor.getString(cursor.getColumnIndex("password"));
            users.add(username + ", " + password);
        }
        cursor.close();
        */

        // Gunakan data dummy
        List<String> users = new ArrayList<>();
        users.add("data 1");
        users.add("data 2");
        users.add("data 3");
        users.add("data 4");
        ListView listView = findViewById(R.id.userListView);
        // Penggunaan array adapter hanya untuk menampilkan data berupa teks
        // Untuk kustomisasi selanjutnya diperlukan pembuatan class adapter tersendiri
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);
    }
}
