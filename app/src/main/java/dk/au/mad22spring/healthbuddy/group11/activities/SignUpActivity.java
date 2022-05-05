package dk.au.mad22spring.healthbuddy.group11.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;

import dk.au.mad22spring.healthbuddy.group11.R;
import dk.au.mad22spring.healthbuddy.group11.databinding.ActivitySignUpBinding;
import dk.au.mad22spring.healthbuddy.group11.utilities.Constants;
import dk.au.mad22spring.healthbuddy.group11.utilities.PreferenceManager;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding signUpBinding;
    private PreferenceManager preferenceManager;



    public SignUpActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signUpBinding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();


    }

    private void setListeners() {
        signUpBinding.textSignIn.setOnClickListener(v -> onBackPressed());
        signUpBinding.btnSignUp.setOnClickListener(view -> {
            if (isValidSignUpDetails()) {
                signUp();
            }
        });
        signUpBinding.layoutImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void signUp() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put(Constants.Key_Name,signUpBinding.inputName.getText().toString());
        user.put(Constants.Key_Email, signUpBinding.inputEmail.getText().toString());
        user.put(Constants.Key_Password, signUpBinding.inputPassword.getText().toString());
        database.collection(Constants.Key_Collection_users)
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    loading(false);
                    preferenceManager.putBoolean(Constants.Key_is_Signed_In, true);
                    preferenceManager.putString(Constants.Key_User_ID, documentReference.getId());
                    preferenceManager.putString(Constants.Key_Name, signUpBinding.inputName.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .addOnFailureListener(exception -> {
                    loading(false);
                    showToast(exception.getMessage());
                });
    }

    private Boolean isValidSignUpDetails(){
        if(signUpBinding.inputName.getText().toString().trim().isEmpty()){
            showToast("Enter name");
            return false;
        } else if(signUpBinding.inputEmail.getText().toString().trim().isEmpty()){
            showToast("Enter email");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(signUpBinding.inputEmail.getText().toString()).matches()){
            showToast("Enter valid email");
            return false;
        } else if(signUpBinding.inputPassword.getText().toString().trim().isEmpty()){
            showToast("Enter password");
            return false;
        } else if(signUpBinding.inputConfirmPassword.getText().toString().trim().isEmpty()){
            showToast("Confirm your password");
            return false;
        } else if(!signUpBinding.inputPassword.getText().toString().equals(signUpBinding.inputConfirmPassword.getText().toString())) {
            showToast("Password and confirm password must be same");
            return false;
        } else{
            return true;
        }

    }

    private void loading(boolean isLoading) {
        if(isLoading){
            signUpBinding.btnSignUp.setVisibility(View.INVISIBLE);
            signUpBinding.progressBar.setVisibility(View.VISIBLE);
        }else {
            signUpBinding.progressBar.setVisibility(View.INVISIBLE);
            signUpBinding.btnSignUp.setVisibility(View.VISIBLE);
        }
    }
}