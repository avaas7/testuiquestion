package com.ayata.question.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ayata.question.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity {

    ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

    private boolean validateEmail() {
        String emailInput = binding.textInputEmail.getEditText().toString().trim();

        if(emailInput.isEmpty())
        {
            binding.textInputEmail.setError("Field can't be empty");
            return false;
        }
        else
        {
            binding.textInputEmail.setError(null);
         //    binding.textInputEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {

        String username = binding.textInputUsername.getEditText().getText().toString().trim();
        if(username.isEmpty()){
            binding.textInputUsername.setError("Field can't be empty");
            return false;
        } else if (username.length() > 15) {
            binding.textInputUsername.setError("Username too long");
            return false;
        } else {
            binding.textInputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = binding.textInputPassword.getEditText().toString().trim();

        if(passwordInput.isEmpty())
        {
            binding.textInputPassword.setError("Field can't be empty");
            return false;
        }
        else
        {
            binding.textInputPassword.setError(null);
            //    binding.textInputEmail.setErrorEnabled(false);
            return true;
        }
    }



    public void confirmInput(View view) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

    }
}