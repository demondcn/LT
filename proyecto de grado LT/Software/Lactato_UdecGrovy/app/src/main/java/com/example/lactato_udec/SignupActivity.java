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

import com.example.lactato_udec.databinding.ActivitySigupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySigupBinding binding;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sigup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivitySigupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DatabaseHelper(this);

        binding.SignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = binding.SignupEmail.getText().toString();
                String password = binding.SignupPassword.getText().toString();
                String confirmPassword = binding.SignupConfirm.getText().toString();
                if(email.equals("") || password.equals("") || confirmPassword.equals(""))
                    Toast.makeText(SignupActivity.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                else {
                    if(password.equals(confirmPassword)){
                        Boolean checkUserEmail = db.CheckUser(email);
                        if (!checkUserEmail){
                            long userId = db.insertDate(email, password);
                            if (userId != -1){
                                Toast.makeText(SignupActivity.this, "Los datos han sido guardados correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity2.class);
                                intent.putExtra("userId", userId);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignupActivity.this, "Ha ocurrido un error al guardar los datos", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(SignupActivity.this, "El email ya existe, intenta con otro", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(SignupActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity2.class);
                startActivity(intent);
            }
        });
    }
}
