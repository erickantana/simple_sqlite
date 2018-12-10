package id.co.freshmeat.simple;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUserActivity extends AppCompatActivity {

    SqlHelper sqlHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        sqlHelper = new SqlHelper(getApplicationContext());
        db = sqlHelper.getWritableDatabase();

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username, password;
                username = findViewById(R.id.username);
                password = findViewById(R.id.password);

                ContentValues values = new ContentValues();
                values.put("username", username.getText().toString());
                values.put("password", password.getText().toString());

                db.insert("user", null, values);
                Toast.makeText(CreateUserActivity.this,
                        "Data dengan username : " + username.getText().toString() + " berhasil disimpan",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
