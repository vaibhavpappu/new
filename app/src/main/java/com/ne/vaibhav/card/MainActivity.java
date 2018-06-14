package com.ne.vaibhav.card;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
Button b3;
private FirebaseAuth firebaseAuth;
TextView t1;
EditText user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), MsgActivity.class));
        }

        setContentView(R.layout.activity_main);
t1=findViewById(R.id.text);
user=findViewById(R.id.username);
pass=findViewById(R.id.password);
b3=findViewById(R.id.button1);
b3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String u=user.getText().toString().trim();
        String p=pass.getText().toString().trim();

 if (u.isEmpty()||p.isEmpty()) {
      user.setError("kaytri taka");
     pass.setError("kaytri taka");
      user.requestFocus();
      pass.requestFocus();
 }

 else
 {
     firebaseAuth.signInWithEmailAndPassword(u, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(MainActivity.this, MsgActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

       }
});
t1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(i);
    }
});
    }

}
