package id.co.freshmeat.simple;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class UserEditAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<User> users;
    SQLiteDatabase writeableDB;

    public UserEditAdapter(Context context, List<User> users, SQLiteDatabase writeableDB) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.users = users;
        this.writeableDB = writeableDB;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.user_item, null);

        TextView content = view.findViewById(R.id.content);
        content.setText(users.get(position).username + ", " + users.get(position).password);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                View dialogView = inflater.inflate(R.layout.edit_dialog, null);
                dialogBuilder.setView(dialogView);

                final Dialog dialog = dialogBuilder.create();

                final EditText username = dialogView.findViewById(R.id.username);
                final EditText password = dialogView.findViewById(R.id.password);
                username.setText(users.get(position).username);
                password.setText(users.get(position).password);

                Button changeButton = dialogView.findViewById(R.id.changeBtn);
                changeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentValues values = new ContentValues();
                        values.put("username", username.getText().toString());
                        values.put("password", password.getText().toString());

                        writeableDB.update("user", values,
                                "username = ?", new String[] { users.get(position).username });
                        users.get(position).username = username.getText().toString();
                        users.get(position).password = password.getText().toString();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                Button cancelButton = dialogView.findViewById(R.id.cancelBtn);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }
}
