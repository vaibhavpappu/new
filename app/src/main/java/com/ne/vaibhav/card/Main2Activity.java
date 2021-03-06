package com.ne.vaibhav.card;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;

//    private TextView textViewSignin;

    private ProgressDialog progressDialog;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        if (firebaseAuth.getCurrentUser() != null) {
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), MsgActivity.class));
        }

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
//        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonSignup = (Button) findViewById(R.id.button1);

        progressDialog = new ProgressDialog(this);

buttonSignup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        registerUser();


    }
});
        //attaching listener to button
//        buttonSignup.setOnClickListener(this);
        // textViewSignin.setOnClickListener(this);
    }

    private void registerUser() {

        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
       if (email.isEmpty()||password.isEmpty())
       {
           editTextEmail.setError("Taka kay tari");
            editTextEmail.requestFocus();
           editTextPassword.setError("Taka kay tari");
           editTextPassword.requestFocus();

       }
else {


           //if the email and password are not empty
           //displaying a progress dialog

           progressDialog.setMessage("Registering Please Wait...");
           progressDialog.show();

           //creating a new user
           firebaseAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           //checking if success
                           if (task.isSuccessful()) {
                               finish();
                               startActivity(new Intent(getApplicationContext(), MsgActivity.class));
                           } else {
                               //display some message here
                               Toast.makeText(Main2Activity.this, "Registration Error", Toast.LENGTH_LONG).show();
                           }
                           progressDialog.dismiss();
                       }
                   });
       }
    }
}
