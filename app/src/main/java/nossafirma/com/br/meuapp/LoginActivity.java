package nossafirma.com.br.meuapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class LoginActivity extends AppCompatActivity {

    private String defaultLogin;
    private String defaultPass;
    private Boolean keepConnected = false;

    private TextInputLayout tilUser;
    private TextInputLayout tilPass;
    private EditText etUser;
    private EditText etPass;
    private CheckBox cbKeepConnected;

    private LoginButton lbFacebook;
    private CallbackManager callbackManager;

    private SharedPreferences preferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Obtem dados e define SPUtils.
        getDefaultLoginValues();

        callbackManager = CallbackManager.Factory.create();
        lbFacebook = (LoginButton) findViewById(R.id.lbFacebookLogin);

        FacebookSdk.sdkInitialize(LoginActivity.this); //getApplicationContext());
        AppEventsLogger.activateApp(LoginActivity.this);

        // Procedimentos para conexão via facebook.
        facebookSignIn();
    }

    public void signIn(View view) {

        getDefaultLoginValues();

        if (keepConnected) {
            etUser.setText(defaultLogin);
            etPass.setText(defaultPass);
            cbKeepConnected.setChecked(keepConnected);
        } else {

            etUser = (EditText) findViewById(R.id.etUser);
            etPass = (EditText) findViewById(R.id.etPass);
            cbKeepConnected = (CheckBox) findViewById(R.id.cbKeepConnected);
        }

        String user = etUser.getText().toString();// .getEditText().getText().toString();
        String pass = etPass.getText().toString(); // tilPass.getEditText().getText().toString();

        if (user.equals("")) {
            etUser.requestFocus();
            Toast.makeText(this, R.string.login_required_user, Toast.LENGTH_LONG).show();
            return;
        }

        if (pass.equals("")) {
            etPass.setFocusable(true);
            Toast.makeText(this, R.string.login_required_password, Toast.LENGTH_LONG).show();
            return;
        }

        if (!defaultLogin.equals(user)) {
            etUser.requestFocus();
            Toast.makeText(this, R.string.login_invalid_user, Toast.LENGTH_LONG).show();
            return;
        } else {
            if (!defaultPass.equals(pass)) {
                etPass.requestFocus();
                Toast.makeText(this, R.string.login_invalid_password, Toast.LENGTH_LONG).show();
                return;
            }
        }

        // Atualiza Shared Preferences
        updateDefaultLoginValues(user, pass, cbKeepConnected.isChecked());

        // Inserir registro na base.

        // Carrega App.
        loadApp();
    }

    public void facebookSignIn() {

        lbFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loadApp();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void loadApp() {
        startActivity(new Intent(LoginActivity.this, NavigationDrawerActivity.class));
        this.finish();
    }

    private void getDefaultLoginValues() {
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        defaultLogin = preferences.getString("defaultLogin", null);
        defaultPass = preferences.getString("defaultPass", null);
        keepConnected = preferences.getBoolean("keepConnected", false);
    }

    private void updateDefaultLoginValues(String login, String pass, Boolean keepConnected) {
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("defaultLogin", login);
        editor.putString("defaultLogin", pass);
        editor.putBoolean("keepConnected", keepConnected);
        editor.apply(); // Assíncrono
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        callbackManager.onActivityResult(requestCode, resultCode, intent);
    }
}
