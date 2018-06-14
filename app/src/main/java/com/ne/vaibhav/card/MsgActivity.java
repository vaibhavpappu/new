package com.ne.vaibhav.card;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MsgActivity extends AppCompatActivity {
Toolbar toolbar;
EditText mesage;
Button btn;
TextView m;
TextView chat;
FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        mesage=findViewById(R.id.msg);
toolbar=findViewById(R.id.toolbar);
btn=findViewById(R.id.send);
chat=findViewById(R.id.conversation);
m=findViewById(R.id.lmsg);
setSupportActionBar(toolbar);

DatabaseReference myref2=database.getReference().child("class");
myref2.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String value=dataSnapshot.getValue(String.class);
    m.setText(value);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

        Toast.makeText(getApplicationContext(),"failed to show",Toast.LENGTH_LONG).show();
    }
});



btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String email="vaibha";
      //  DatabaseReference myRef = database.getReference().child("messages");
        //  DatabaseReference myReff = database.getReference("addr");
      //  for (int count=0;count<=1;count++)
      //  {
          //  String cnt=Integer.toString(count);
            DatabaseReference myRef1 = database.getReference(email);
            Map map = new HashMap<String, Object>();
            map.put("msg", mesage);
            map.put("sender", email);
            map.put("time", "10:10");
            myRef1.setValue(map);
      //  }


/*
        DatabaseReference myRef1 = database.getReference().child("Messages");
        Map map=new HashMap<String,Object>();
        map.put("msg","hiii");
        map.put("sender",email);
        map.put("time","10:10");
        myRef1.push().setValue(map);*/
        }
});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id=item.getItemId();
        if (res_id==R.id.signout)
        {
FirebaseAuth.getInstance().signOut();
Intent i=new Intent(MsgActivity.this,MainActivity.class);
startActivity(i);
        }
        return true;
    }

}
