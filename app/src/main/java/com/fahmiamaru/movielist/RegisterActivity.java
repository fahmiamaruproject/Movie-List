package com.fahmiamaru.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, passwordconfirm;
    Button login,register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister);
        passwordconfirm = findViewById(R.id.passwordconfirm);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

        register.setOnClickListener(v -> {
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();
            String pwdconf = passwordconfirm.getText().toString();

            if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                Toast.makeText(getApplicationContext(), "Harap Diisi Semua!", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()) {
                Toast.makeText(getApplicationContext(), "Isi Email Dengan Benar!", Toast.LENGTH_SHORT).show();
            } else if(txt_password.length()<6){
                Toast.makeText(getApplicationContext(), "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
            }else if (!txt_password.equals(pwdconf)){
                Toast.makeText(getApplicationContext(), "Password tidak sama", Toast.LENGTH_SHORT).show();
            }else {
                mAuth.createUserWithEmailAndPassword(txt_email,txt_password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            } else{
                                Toast.makeText(getApplicationContext(), "Kamu tidak bisa daftar menggunakan email atau password ini", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}