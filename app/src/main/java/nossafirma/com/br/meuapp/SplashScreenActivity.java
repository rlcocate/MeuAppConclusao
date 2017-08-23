package nossafirma.com.br.meuapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import nossafirma.com.br.meuapp.adapter.LoginAdapter;
import nossafirma.com.br.meuapp.api.IRetrofitApi;
import nossafirma.com.br.meuapp.api.RetrofitClient;
import nossafirma.com.br.meuapp.model.Login;
import nossafirma.com.br.meuapp.sqlite.DBHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_DISPLAY_LENGTH = 3000;
    private boolean loadedData = false;

    private String usuario;
    private String senha;
    private IRetrofitApi retrofitApi;
    private LoginAdapter loginAdapter;
    private SharedPreferences preferences = null;
    private DBHelper dbHelper = null;

    public Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Cria banco de dados.
//        createDB();

        // Carrega dados da Api.
        loadData();

        // Carrega splash screen do app.
        loadSplash();
    }

    private void loadSplash() {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animation_splash_screen);

        ImageView iv = (ImageView) findViewById(R.id.ivSplash);

        if (iv != null) {
            iv.clearAnimation();
        }

        iv.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    private void loadData() {

        retrofitApi = RetrofitClient.getApiData();

        retrofitApi.getDefaultAutentication().enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {

                    // Retorno da WebApi.
                    Login login = response.body();

                    // Armazena valores padrão de login.
                    createLoginDefaultValues(login);
                }

                loadedData = true;
                Toast.makeText(SplashScreenActivity.this, "Acesso efetuado e dados gravados com sucesso!", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.i("ERRO", t.getStackTrace().toString());
                Toast.makeText(SplashScreenActivity.this, "Erro ao acessar servidor: " + t.getStackTrace().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void createLoginDefaultValues(Login login){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("defaultLogin", (login.getFblogin() == null ? login.getUsuario() : login.getFblogin()));
        editor.putString("defaultPass", login.getSenha());
        editor.putBoolean("keepConnected", false);
        editor.apply(); // Assíncrono
    }

    private void createDB() {
        dbHelper = new DBHelper(context);
    }
}
