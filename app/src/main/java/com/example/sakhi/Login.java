package com.example.sakhi;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;
import java.util.Objects;;

public class Login extends AppCompatActivity {
    Button LogInButton, RegisterButton ;
    EditText Email, Password ;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    String UserName;
    URL url;
    public static final String UserEmail = "";
    public static final String userName="";


//    Button got_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        LogInButton = (Button) findViewById(R.id.buttonLogin);

        RegisterButton = (Button) findViewById(R.id.buttonRegister);

        Email = (EditText) findViewById(R.id.editEmail);
        Password = (EditText) findViewById(R.id.editPassword);

        sqLiteHelper = new SQLiteHelper(this);


        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling EditText is empty or no method.
                CheckEditTextStatus();

                // Calling login method.
                LoginFunction();


            }
        });


        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);

            }
        });
    }
        // Login function starts from here.
        public void LoginFunction(){

            if(EditTextEmptyHolder) {

                // Opening SQLite database write permission.
                sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

                // Adding search email query to cursor.
                cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

                while (cursor.moveToNext()) {

                    if (cursor.isFirst()) {

                        cursor.moveToFirst();

                        // Storing Password associated with entered email.
                        TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));
                        UserName=cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name));

                        // Closing cursor.
                        cursor.close();
                    }
                }

                // Calling method to check final result ..
                CheckFinalResult();

            }
            else {

                //If any of login EditText empty then this block will be executed.
                Toast.makeText(Login.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

            }

        }

        // Checking EditText is empty or not.
        public void CheckEditTextStatus(){

            // Getting value from All EditText and storing into String Variables.
            EmailHolder = Email.getText().toString();
            PasswordHolder = Password.getText().toString();

            // Checking EditText is empty or no using TextUtils.
            if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

                EditTextEmptyHolder = false ;

            }
            else {

                EditTextEmptyHolder = true ;
            }
        }

        // Checking entered password from SQLite database email associated password.
        public void CheckFinalResult(){

            if(TempPassword.equalsIgnoreCase(PasswordHolder))
            {

                Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_LONG).show();

                // Going to Dashboard activity after login success message.
                Intent intent = new Intent(Login.this, DashboardLayout.class);

                // Sending Email to Dashboard Activity using intent.
                intent.putExtra(userName,UserName );

                startActivity(intent);


            }
            else {

                Toast.makeText(Login.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

            }
            TempPassword = "NOT_FOUND" ;

            if(Objects.equals(Email.getText().toString(), "admin")&& Objects.equals(Password.getText().toString(),"admin"))
            {
                Toast.makeText(Login.this,"You have Authenticated Successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this, Admin.class);
                startActivity(intent);
            }else
            {
                Toast.makeText(Login.this,"Authentication Failed",Toast.LENGTH_LONG).show();
            }

        }

    }

