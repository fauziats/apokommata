package com.apokommata.apokommata.View;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.apokommata.apokommata.App;
import com.apokommata.apokommata.R;
import com.apokommata.apokommata.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database;
    DatabaseReference userRef;
    ProgressDialog progressDialog;
    SharedPreferences preferences;

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bg_signin)
    ImageView bgSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");

        preferences = getSharedPreferences(App.USER_PREFERENCE,MODE_PRIVATE);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Logging in...");

        Picasso.with(this)
                .load(R.drawable.signup)
                .resize(600,500)
                .into(bgSignIn);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAppAuth) {
                FirebaseUser user = firebaseAppAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(App.ID,user.getUid());
                    editor.putString(App.EMAIL,user.getEmail());
                    editor.apply();

                } else {
                    // User is signed out
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @OnClick(R.id.btn_sign_in)
    void signIn(){
        if(!Utils.isEmpty(etEmail) && !Utils.isEmpty(etPassword)) {
            if(!progressDialog.isShowing())
                progressDialog.show();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();

                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, R.string.signin_failed,
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(this,"Username dan Password tidak boleh kosong",Toast.LENGTH_LONG).show();
        }
    }
}
