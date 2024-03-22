package com.example.baivolley.contronller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baivolley.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    EditText edtName, edtEmail, edtPassword;
    Intent intentSignup;
    ImageButton imvRegister;
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setupUI();
        setupProcess();
    }

    private void setupProcess() {
        setupCreateAccount();
    }

    public void setupCreateAccount() {
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count,
                    int after
            ) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before,
                    int count
            ) {
                password = s.toString();

                if (password.length() >= 8) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(password);
                    boolean passwordsMatch = matcher.find();
                    if (passwordsMatch) {
                        textInputLayout.setHelperText("Your Password are Strong");
                        textInputLayout.setError("");
                    } else {
                        textInputLayout.setError(
                                "Mix of letters(upper and lower case), number and symbols");
                    }
                } else {
                    textInputLayout.setHelperText("Password must 8 Characters Long");
                    textInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imvRegister.setOnClickListener(v -> {
            try {
                String name = edtName.getText()
                                     .toString(),
                        email = edtEmail.getText()
                                        .toString();

                if (name.length() == 0 || email.length() == 0) {
                    Toast.makeText(this, "Nhap lai", Toast.LENGTH_SHORT)
                         .show();

                } else {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                         .show();
                    loadRegister();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void loadRegister() {
        intentSignup = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intentSignup);
    }

    private void setupUI() {
        edtName = findViewById(R.id.editText_name);
        edtEmail = findViewById(R.id.editText_email1);
//        edtPassword = findViewById(R.id.editText_password1);
        textInputLayout = findViewById(R.id.editText_password1);
        textInputEditText = findViewById(R.id.textinput_password1);
        imvRegister = findViewById(R.id.imageView_register);
    }
}