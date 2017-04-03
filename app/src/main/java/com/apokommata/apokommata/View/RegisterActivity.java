package com.apokommata.apokommata.View;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    ProgressDialog progressDialog;
    SharedPreferences preferences;

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_full_name)
    EditText etFullName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.sp_location)
    Spinner spLocation;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.bg_signup)
    ImageView bgSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");

        preferences = getSharedPreferences(App.USER_PREFERENCE,MODE_PRIVATE);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Creating new User...");

        Picasso.with(this)
                .load(R.drawable.signup)
                .resize(600,500)
                .into(bgSignUp);

        setupLocationSpinner();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAppAuth) {
                FirebaseUser user = firebaseAppAuth.getCurrentUser();
                if (user != null) {
                    Log.e("REGISTER", "SUKSES!!");
                    // User is signed in
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(App.ID,user.getUid());
                    editor.putString(App.EMAIL,user.getEmail());
                    editor.apply();

                    Toast.makeText(RegisterActivity.this,"HEHEHE",Toast.LENGTH_LONG).show();

                } else {
                    // User is signed out
                }
            }
        };
    }

    @OnItemSelected(R.id.sp_location)
    void selectLocation(){

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

    @OnClick(R.id.btn_sign_up)
    void signUp(){
        if(!Utils.isEmpty(etEmail)
                && !Utils.isEmpty(etPassword)
                && !Utils.isEmpty(etFullName)
                && !Utils.isEmpty(etConfirmPassword)
                && !Utils.isEmpty(etPhone)
                && (spLocation.getSelectedItemPosition() != 0)) {
            if(etPassword.getText().toString().length() < 8){
                if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, R.string.signin_failed,
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                } else {
                    Toast.makeText(this,"Konfirmasi password tidak cocok",Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this,"Password tidak boleh kurang dari 8 karakter",Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this,"Data tidak boleh kosong",Toast.LENGTH_LONG).show();
        }
    }

    void setupLocationSpinner(){
        String[] cities = new String[]{
                "Select your location...",
                "Yogyakarta",
                "Jakarta",
                "Bandung",
                "Surabaya"
        };

        final List<String> citiesList = new ArrayList<>(Arrays.asList(cities));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item,citiesList){

            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spLocation.setAdapter(spinnerArrayAdapter);
    }
}

