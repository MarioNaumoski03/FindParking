package com.example.findparking;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationPage extends AppCompatActivity {

    SQLiteDatabase database;
    EditText input_namesurname;
    EditText input_username;
    EditText input_password;
    String namesurname;
    String username;
    String password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        database = openOrCreateDatabase("Database", MODE_PRIVATE, null);
    }

    public void RegisterBtn(View view) {
        input_namesurname=(EditText)findViewById(R.id.namesurnameR);
        input_username=(EditText)findViewById(R.id.usernameR);
        input_password=(EditText)findViewById(R.id.passR);
        if (input_namesurname.getText().toString().trim().length() == 0 || input_username.getText().toString().trim().length() == 0 || input_password.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_LONG).show();
        } else {
            namesurname = input_namesurname.getText().toString().trim();
            username = input_username.getText().toString().trim();
            password = input_password.getText().toString().trim();

            Cursor c1 = database.rawQuery("SELECT * FROM Users WHERE username LIKE '" + username + "'", null);
            if (c1.moveToFirst()) {
                Toast.makeText(this, "Username doesn't exist!", Toast.LENGTH_LONG).show();
                c1.close();
                input_username.setText("");
            } else if (password.length() < 5) {
                Toast.makeText(this, "Password must have minimum 5 characters!", Toast.LENGTH_LONG).show();
            } else {
                database.execSQL("INSERT INTO Users VALUES('" + namesurname + "', '" + username + "', '" + password + "' );");
                Toast.makeText(this, "You are succesfully registered!", Toast.LENGTH_LONG).show();
               // c1.close();
                Intent intent1=new Intent(this,CityList.class);
                startActivity(intent1) ;
            }

        }




    }
}