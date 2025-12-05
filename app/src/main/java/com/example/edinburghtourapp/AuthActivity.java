package com.example.edinburghtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthActivity extends ComponentActivity {

    // ---------- FIELDS ----------
    private FirebaseAuth auth;

    // Inputs on the screen
    private EditText etEmail;
    private EditText etPassword;

    // Small text label to show messages
    private TextView tvMsg;

    // Google sign-in
    private GoogleSignInClient googleClient;
    private ActivityResultLauncher<Intent> googleLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);
        
        auth = FirebaseAuth.getInstance();

        // If already logged in, skip straight to profile
        FirebaseUser current = auth.getCurrentUser();
        if (current != null) {
            goProfile();
            return;
        }

        // Show login / register screen
        setContentView(R.layout.activity_auth);

        // ---------- LINK JAVA VARIABLES TO XML VIEWS ----------
        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvMsg      = findViewById(R.id.tvMsg);

        Button btnSignUp  = findViewById(R.id.btnSignUp);
        Button btnSignIn  = findViewById(R.id.btnSignIn);
        Button btnGoogle  = findViewById(R.id.btnGoogle);
        TextView tvForgot = findViewById(R.id.tvForgot); // only declared ONCE

        // ---------- EMAIL / PASSWORD BUTTONS ----------
        btnSignUp.setOnClickListener(v -> createAccount());
        btnSignIn.setOnClickListener(v -> signIn());

        // ---------- FORGOT PASSWORD ----------
        tvForgot.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (!isValidEmail(email)) {
                toast(getString(R.string.msg_enter_email_first));
                return;
            }
            auth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(x -> toast(getString(R.string.msg_reset_sent)))
                    .addOnFailureListener(e -> toast(e.getMessage()));
        });

        // ---------- GOOGLE SIGN-IN ----------
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

        googleClient = GoogleSignIn.getClient(this, gso);

        googleLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getData() == null) {
                        toast(getString(R.string.msg_google_cancelled));
                        return;
                    }
                    try {
                        GoogleSignInAccount acct = GoogleSignIn
                                .getSignedInAccountFromIntent(result.getData())
                                .getResult(ApiException.class);

                        if (acct == null) {
                            toast(getString(R.string.msg_google_failed));
                            return;
                        }

                        AuthCredential cred =
                                GoogleAuthProvider.getCredential(acct.getIdToken(), null);

                        auth.signInWithCredential(cred)
                                .addOnSuccessListener(r -> goProfile())
                                .addOnFailureListener(e -> toast(e.getMessage()));
                    } catch (ApiException e) {
                        toast(getString(R.string.msg_google_error_prefix) + e.getStatusCode());
                    }
                });

        btnGoogle.setOnClickListener(v ->
                googleClient.signOut().addOnCompleteListener(task ->
                        googleLauncher.launch(googleClient.getSignInIntent()))
        );
    }

    // ---------- CREATE ACCOUNT (EMAIL/PASSWORD) ----------
    private void createAccount() {
        String email = etEmail.getText().toString().trim();
        String pass  = etPassword.getText().toString();

        if (!isValidEmail(email)) {
            tvMsg.setText(R.string.msg_enter_valid_email);
            return;
        }
        if (pass.length() < 8) {
            tvMsg.setText(R.string.msg_password_min_8);
            return;
        }

        tvMsg.setText(R.string.msg_creating_account);
        auth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(result -> goProfile())
                .addOnFailureListener(e -> tvMsg.setText(e.getMessage()));
    }

    // ---------- SIGN IN (EMAIL/PASSWORD) ----------
    private void signIn() {
        String email = etEmail.getText().toString().trim();
        String pass  = etPassword.getText().toString();

        if (!isValidEmail(email) || TextUtils.isEmpty(pass)) {
            tvMsg.setText(R.string.msg_email_password_required);
            return;
        }

        tvMsg.setText(R.string.msg_signing_in);
        auth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(result -> goProfile())
                .addOnFailureListener(e -> tvMsg.setText(e.getMessage()));
    }

    // ---------- HELPERS ----------

    // Simple email format check
    private boolean isValidEmail(String e) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches();
    }

    // Go to profile screen
    private void goProfile() {
        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }

    private void toast(String m) {
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }
}
