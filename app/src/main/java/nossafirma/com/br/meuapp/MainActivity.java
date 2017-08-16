package nossafirma.com.br.meuapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nossafirma.com.br.meuapp.api.IRetrofitApi;

public class MainActivity extends AppCompatActivity {

    private String defaultLogin;
    private String defaultPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        defaultLogin = preferences.getString("defaultLogin", null);
        defaultPass = preferences.getString("defaultPass", null);
    }
}
