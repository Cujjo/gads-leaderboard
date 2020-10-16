package com.gads.leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gads.leaderboard.datasource.PostClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionDetailsActivity extends AppCompatActivity {
    private Button submitButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText githubEditText;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_details);

        // Toolbar toolbar = findViewById(R.id.toolbar_submission);
        // setSupportActionBar(toolbar);
        submitButton = findViewById(R.id.button_submit_form);
        firstNameEditText = findViewById(R.id.edit_text_first_name);
        lastNameEditText = findViewById(R.id.edit_text_last_name);
        emailEditText = findViewById(R.id.edit_text_email);
        githubEditText = findViewById(R.id.edit_text_github);
        backButton = findViewById(R.id.app_bar_back_image);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitProject();
            }
        });

        backButton.bringToFront();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubmissionDetailsActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });
    }

    public void submitProject() {
        if(isValidInputs()) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.modal_confirm_submission, null);
            Button yesButton = view.findViewById(R.id.button_yes);
            ImageView cancelButton = view.findViewById(R.id.icon_cancel_submission);
            AlertDialog alertDialog = new AlertDialog.Builder(this).setView(view).create();
            alertDialog.show();
            
            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postToGoogleServers();
                    alertDialog.dismiss();
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
        }
    }

    private boolean isValidInputs() {
        if(firstNameEditText.getText().toString().trim().equals("")) {
            firstNameEditText.setError("First name is required");
            return false;
        }
        if(lastNameEditText.getText().toString().trim().equals("")) {
            lastNameEditText.setError("Last name is required");
            return false;
        }
        if(emailEditText.getText().toString().trim().equals("")
                || !Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString().trim()).matches()) {
            emailEditText.setError("A valid email is required");
            return false;
        }
        if(githubEditText.getText().toString().trim().equals("")) {
            githubEditText.setError("Github link is required");
            return false;
        }
        return true;
    }

    private void postToGoogleServers() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String github = githubEditText.getText().toString().trim();
        PostClient.getGoogleDocs().submitProject(firstName, lastName, email, github)
            .enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(SubmissionDetailsActivity.this, "" + response.code(), Toast.LENGTH_LONG).show();
                        LayoutInflater inflater = LayoutInflater.from(SubmissionDetailsActivity.this);
                        View successModal = inflater.inflate(R.layout.modal_submission_success, null);
                        AlertDialog alertDialog = new AlertDialog.Builder(SubmissionDetailsActivity.this,
                            R.style.Theme_MaterialComponents_Light_Dialog).setView(successModal).create();
                        alertDialog.show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Log.d("TAG", "------fail-----" + t.getMessage());
                    Toast.makeText(SubmissionDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = LayoutInflater.from(SubmissionDetailsActivity.this);
                    View failureModal = inflater.inflate(R.layout.modal_submission_failure, null);
                    AlertDialog alertDialog = new AlertDialog.Builder(SubmissionDetailsActivity.this,
                        R.style.Theme_MaterialComponents_Light_Dialog).setView(failureModal).create();
                    alertDialog.show();
                }
            });
    }
}
