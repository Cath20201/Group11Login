package dk.au.mad22spring.healthbuddy.group11.activities;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import dk.au.mad22spring.healthbuddy.group11.databinding.ActivitySignInBinding;
import dk.au.mad22spring.healthbuddy.group11.utilities.Constants;
import dk.au.mad22spring.healthbuddy.group11.utilities.PreferenceManager;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding signInBinding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new PreferenceManager(getApplicationContext());

        signInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(signInBinding.getRoot());
        setListeners();
    }

    private void setListeners() {
        signInBinding.textCreateNewAccount.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));
        signInBinding.btnSignIn.setOnClickListener(v ->{
            if(isValidSignInDetails()){
                signIn();
            }
        });
    }

    private void signIn() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.Key_Collection_users)
                .whereEqualTo(Constants.Key_Email, signInBinding.inputEmail.getText().toString())
                .whereEqualTo(Constants.Key_Password, signInBinding.inputPassword.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult() != null
                            && task.getResult().getDocuments().size() > 0){
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        preferenceManager.putBoolean(Constants.Key_is_Signed_In, true);
                        preferenceManager.putString(Constants.Key_User_ID, documentSnapshot.getId());
                        preferenceManager.putString(Constants.Key_Name, documentSnapshot.getString(Constants.Key_Name));
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else{
                        loading(false);
                        showToast("Unable to sign in");
                    }
                });
    }

    private void loading(boolean isLoading) {
        if(isLoading){
            signInBinding.btnSignIn.setVisibility(View.INVISIBLE);
            signInBinding.progressBar.setVisibility(View.VISIBLE);
        }else {
            signInBinding.progressBar.setVisibility(View.INVISIBLE);
            signInBinding.btnSignIn.setVisibility(View.VISIBLE);
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private Boolean isValidSignInDetails() {
        if(signInBinding.inputEmail.getText().toString().trim().isEmpty()){
            showToast("Enter email");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(signInBinding.inputEmail.getText().toString()).matches()){
            showToast("Enter valid email");
            return false;
        }else if (signInBinding.inputPassword.getText().toString().trim().isEmpty()){
            showToast("Enter password");
            return false;
        } else {
            return true;
        }
    }
}