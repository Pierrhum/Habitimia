package com.example.habitimia.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habitimia.R;
import com.example.habitimia.data.model.Statistics;
import com.example.habitimia.data.model.User;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.ui.SplashActivity;
import com.example.habitimia.ui.login.LoginViewModel;
import com.example.habitimia.ui.login.LoginViewModelFactory;
import com.example.habitimia.util.Server;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);

        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                /***
                 * TODO : Connexion
                 * 1 - Lire base de données
                 * 2 - Récupérer données
                 * Trouver la ligne où usernameEditText.getText().toString() == USER.Username
                 * Si introuvable : erreur : Aucun compte associé à ce pseudo
                 * Sinon si passwordEditText.getText().toString() == USER.password
                 * Instancier User avec les données de la ligne et ouvrir intent HomeActivity
                 * Sinon : erreur : Mot de passe incorrect
                 */
                //1
                String request_params = "username=" + usernameEditText.getText().toString()
                                        + "&" +
                                         "password=" + passwordEditText.getText().toString();

//                JSONObject response = Server.sendRequest("login", request_params);
                JSONObject response = null;
                JSONArray response_all_params = null;
                try {
                    response = new JSONObject(
                            "{\"id\": \"1\",\n" +
                            "\"username\": \"Mia\",\n" +
                            "\"email\": \"mia@love.pierre\",\n" +
                            "\"password\": \"123456\",\n" +
                            "\"avatar\": \"MAGICIAN\",\n" +
                            "\"statistics\": {\n" +
                            "   \"id\": \"1\",\n" +
                            "   \"adventurerClass\": \"A\",\n" +
                            "   \"battlesWon\": 0,\n" +
                            "   \"allBattles\": 0,\n" +
                            "   \"hp\": 10\n" +
                            "    },\n" +
                            "\"guild\": {\n" +
                            "   \"id\": 1,\n" +
                            "   \"name\": \"Dead End\"\n" +
                            "    }\n" +
                            "}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (response == null){
                    Integer errorString = R.string.invalid_password_or_username;
                    showLoginFailed(errorString);
                }else{

                    Statistics stats = null;
                    User user = null;
                    try {
                        JSONObject statsJSON = response.getJSONObject("statistics");
                        stats = new Statistics(statsJSON);
                        user = new User(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    user.setStatistics(stats);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user",  user);
                    startActivity(intent);
                    finish();
                }

            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}