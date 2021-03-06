package id.co.freshmeat.simple;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DeleteUserActivity extends AppCompatActivity {

    SqlHelper sqlHelper;
    SQLiteDatabase readableDb, writeableDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        sqlHelper = new SqlHelper(getApplicationContext());
        writeableDb = sqlHelper.getWritableDatabase();
        readableDb = sqlHelper.getReadableDatabase();

        String[] columns = { "username", "password" };

        Cursor cursor = readableDb.query("user", columns, null, null, null, null, null);
        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            User user = new User();
            user.username = cursor.getString(cursor.getColumnIndex("username"));
            user.password = cursor.getString(cursor.getColumnIndex("password"));
            users.add(user);
        }
        cursor.close();

        ListView listView = findViewById(R.id.userListView);
        UserDeleteAdapter adapter = new UserDeleteAdapter(this, users, writeableDb);
        listView.setAdapter(adapter);
    }
}
