package com.example.lactato_udec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lactato_udec.databinding.ActivityLogin2Binding;

public class LoginActivity2 extends AppCompatActivity {

    ActivityLogin2Binding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper  = new DatabaseHelper(this);

        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.LoginEmail.getText().toString();
                String password = binding.LoginPassword.getText().toString();

                if(email.equals("") || password.equals(""))
                    Toast.makeText(LoginActivity2.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                    else{
                        Boolean checkCredentials = databaseHelper.CheckUserPassword(email, password);
                        if(checkCredentials==true){
                            Toast.makeText(LoginActivity2.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), seccion_iniciada_student_mode__activity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity2.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                     }
                }
            }
        });
        binding.SignUpRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity2.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}