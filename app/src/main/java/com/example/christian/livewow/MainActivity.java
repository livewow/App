package com.example.christian.livewow;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final TextView textView = (TextView)findViewById(R.id.test1);
        Button test = (Button)findViewById(R.id.button);
        Button load = (Button)findViewById(R.id.loadButton);

        User test1 = new User();
        ContactInfo ciTest1 = new ContactInfo();

        test1.setUsername("Tester1");
        test1.setPassword("Test1234");
        ciTest1.setEmail("tester1@test.com");
        ciTest1.setPhoneNumber("(123)456-7890");
        test1.setContactInfo(ciTest1);

        final Map<String, User> users = new HashMap<>();
        users.put("tester1", test1);

        mFirebaseAuth.signInWithEmailAndPassword(test1.getContactInfo().getEmail(), test1.getPassword())
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            textView.setText("It worked!!");
                            Log.i("firebaseTest", "Connection Successful");
                        } else {
                            textView.setText("It didn't work");
                            Log.i("firebaseTest", "Connection Failed!!");
                        }
                    }
                });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("firebaseTest", "Stuff should be happening!");
                Query query = mDatabase.child("users").equalTo("tester2");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User testRetrieval = dataSnapshot.getValue(User.class);
                        textView.setText("Data Read: " + testRetrieval.getUsername() + " " + testRetrieval.getContactInfo().getPhoneNumber());
                        Log.i("firebaseTest", "Read Successful");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("loadPost:onCancelled", databaseError.toException());
                    }
                });
            }
        });


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("users").setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            textView.setText("Data Written");
                            Log.i("firebaseTest", "Write Successful");
                        } else {
                            textView.setText("Data Write Failed!");
                            Log.i("firebaseTest", "Write Failed!!");
                        }
                    }
                });
            }
        });


//        String username = "Tester2";
//        String password = "Test4321";
//        String email = "tester2@test.com";
//        String phoneNumber = "(908)765-4321";
//
//        ContactInfo ciTest2 = new ContactInfo(phoneNumber, email);
//        User test2 = new User(username, password, ciTest2);
//
//        System.out.println("User: " + test1.getUsername());
//        System.out.println("Password :" + test1.getPassword());
//        System.out.println("Contact Info: ");
//        System.out.println("     Email: " + test1.getContactInfo().getEmail());
//        System.out.println("     Phone Number: " + test1.getContactInfo().getPhoneNumber());
//
//        System.out.println();
//        System.out.println("User: " + test2.getUsername());
//        System.out.println("Password: " + test2.getPassword());
//        System.out.println("Contact Info: ");
//        System.out.println("     Email: " + test2.getContactInfo().getEmail());
//        System.out.println("     Phone Number: " + test2.getContactInfo().getPhoneNumber());
    }
}
