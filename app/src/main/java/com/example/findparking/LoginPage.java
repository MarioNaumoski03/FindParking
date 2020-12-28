package com.example.findparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
SQLiteDatabase database;
EditText input_username;
EditText input_pass;
String NameSurname;
String username;
String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        database=openOrCreateDatabase("Database",MODE_PRIVATE,null);


    }

    public void LoginBtn(View view) {
        input_username=(EditText)findViewById(R.id.Username);
        input_pass=(EditText)findViewById(R.id.Pass);

        if (input_username.getText().toString().trim().length() == 0 || input_pass.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_LONG).show();
        } else {
            Cursor c1 = database.rawQuery("SELECT * FROM Users WHERE username LIKE '" + input_username.getText().toString().trim() + "'", null);
            if (c1.moveToFirst()) {

                username = c1.getString(2);
                password = c1.getString(3);

                if (password.equals(input_pass.getText().toString().trim())) {
                    NameSurname = c1.getString(0);
                    c1.close();
                    Intent intent2 = new Intent(this, CityList.class);
                    //intent.putExtra("Name", NameSurname);
                   // intent.putExtra("Username", username);
                    startActivity(intent2);

                } else {
                    Toast.makeText(this, "Wrong password, try again!", Toast.LENGTH_LONG).show();
                    input_username.setText("");
                    input_pass.setText("");
                    c1.close();
                }
            } else {
                Toast.makeText(this, "This username doeasn't exist!", Toast.LENGTH_LONG).show();
                input_username.setText("");
                input_pass.setText("");
                c1.close();
            }
        }
    }

    public void RegistrationBtn(View view) {
        Intent intent1=new Intent(this,RegistrationPage.class);
        startActivity(intent1);
    }
}