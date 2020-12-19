package com.example.margdarshak;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    AppCompatTextView tvsignup;
    TextInputEditText edEmail, edPassword;
    String emailInput, passwordInput, phoneInput;
    ImageButton ibLogin;
    ProgressBar pr_Bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        pr_Bar = findViewById(R.id.pr_Bar);

        edEmail.addTextChangedListener(new TextWatcher() {
            TextInputLayout errorEmail = findViewById(R.id.olTFEmail);

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneInput = edEmail.getText().toString().trim();
                if (validEmail()) {
                    Toast.makeText(LoginActivity.this, "Email", Toast.LENGTH_SHORT).show();
                }
                if (validPhone()) {
                    if (phoneInput.length() > 10) {
                        edEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
                    }
                    Toast.makeText(LoginActivity.this, "Phone", Toast.LENGTH_SHORT).show();
                    errorEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvsignup = findViewById(R.id.tvsignup);
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, IntroActivity.class));
                finish();
            }
        });

        ibLogin = findViewById(R.id.ibLogin);
        ibLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validPassword()) {
                    pr_Bar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(LoginActivity.this, OTP_varification.class));
                }
                pr_Bar.setVisibility(View.GONE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean validEmail() {
        emailInput = Objects.requireNonNull(edEmail.getText()).toString().trim();
        TextInputLayout errorEmail = findViewById(R.id.olTFEmail);
        if (emailInput.isEmpty()) {
            errorEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            return false;
        } else {
            errorEmail.setError(null);
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean validPassword() {
        passwordInput = Objects.requireNonNull(edPassword.getText()).toString().trim();
        TextInputLayout errorPassword = findViewById(R.id.olTFPassword);
        if (passwordInput.isEmpty()) {
            errorPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            errorPassword.setError("It must be in 8 letters, A-Z, a-z, 0-9, One Special latter");
            return false;
        } else {
            errorPassword.setError(null);
            return true;
        }
    }

    private boolean validPhone() {
        phoneInput = edEmail.getText().toString().trim();
        if (!TextUtils.isEmpty(phoneInput)) {
            return TextUtils.isDigitsOnly(phoneInput);
        } else {
            return true;
        }
    }
}