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

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    ImageButton ibSignup;
    boolean isChechedDone = false;
    String usernameInput, passwordInput, emailInput, confirmpasswordInput, phoneInput;
    private TextInputEditText etUserName, etPassword, etEmail, etConfirmPassword;
    private ProgressBar prBar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUserName = findViewById(R.id.edUserName);
        etUserName.clearFocus();
        etEmail = findViewById(R.id.edEmail);
        etPassword = findViewById(R.id.edPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPass);
        ibSignup = findViewById(R.id.ibSignup);
        prBar = findViewById(R.id.prBar);

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validEmail()) {
                    Toast.makeText(SignupActivity.this, "Email", Toast.LENGTH_SHORT).show();
                }
                if (validPhone()) {
                    phoneInput = etEmail.getText().toString().trim();
                    if (phoneInput.length() > 10) {
                        etEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
                    }
                    Toast.makeText(SignupActivity.this, "Phone", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ibSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validUser() && validPassword() && confirmPassword()) {
                    prBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(SignupActivity.this, OTP_varification.class));
                }
                prBar.setVisibility(View.GONE);
            }
        });

        final LottieAnimationView lottieCheckedDone = findViewById(R.id.lacheckbox);
        animation(lottieCheckedDone);
    }

    private void animation(final LottieAnimationView lottieCheckedDone) {
        lottieCheckedDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChechedDone) {
                    lottieCheckedDone.setSpeed(-1);
                    lottieCheckedDone.playAnimation();
                    isChechedDone = false;
                } else {
                    lottieCheckedDone.setSpeed(1);
                    lottieCheckedDone.playAnimation();
                    isChechedDone = true;
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean validUser() {
        usernameInput = etUserName.getText().toString().trim();
        TextInputLayout errorUserName = findViewById(R.id.outlinedTextField);
        if (usernameInput.isEmpty()) {
            errorUserName.setError("User Name required");
            return false;
        } else if (usernameInput.length() > 25) {
            errorUserName.setError("Username too long");
            return false;
        } else {
            errorUserName.setError(null);
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean validPassword() {
        passwordInput = Objects.requireNonNull(etPassword.getText()).toString().trim();
        TextInputLayout errorPassword = findViewById(R.id.outlinedTextField3);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean validEmail() {
        emailInput = etEmail.getText().toString().trim();
        TextInputLayout errorEmail = findViewById(R.id.outlinedTextField2);
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

    private boolean validPhone() {
        phoneInput = etEmail.getText().toString().trim();
        TextInputLayout errorEmail = findViewById(R.id.outlinedTextField2);
        if (!TextUtils.isEmpty(phoneInput)) {
            return TextUtils.isDigitsOnly(phoneInput);
        } else {
            errorEmail.setError(null);
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean confirmPassword() {
        confirmpasswordInput = Objects.requireNonNull(etConfirmPassword.getText()).toString().trim();
        passwordInput = Objects.requireNonNull(etPassword.getText()).toString().trim();
        TextInputLayout errorConfirmPass = findViewById(R.id.outlinedTextField4);
        if (confirmpasswordInput.isEmpty()) {
            errorConfirmPass.setError("Field can't be empty");
            return false;
        } else if (!confirmpasswordInput.equals(passwordInput)) {
            errorConfirmPass.setError("Password does not match");
            return false;
        } else {
            errorConfirmPass.setError(null);
            return true;
        }
    }
}