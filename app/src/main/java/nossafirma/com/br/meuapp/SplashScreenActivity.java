package nossafirma.com.br.meuapp;

import android.content.Intent;
import android.os.Handler;
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
import nossafirma.com.br.meuapp.model.LoginResponse;
import nossafirma.com.br.meuapp.sqlite.DBHelper;
import nossafirma.com.br.meuapp.sqlite.LoginDAO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_DISPLAY_LENGTH = 3000;
    private String usuario;
    private String senha;
    private IRetrofitApi retrofitApi;
    private LoginAdapter loginAdapter;
    boolean loadedData = false;
    LoginDAO loginDAO = null;
    DBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Cria banco de dados.
        createDB();

        // Carrega dados da Api
//        getApiData();

//        loadData();

//        loadSplash();
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
//                while (!loadedData){}
                Toast.makeText(SplashScreenActivity.this, "Usuário: " + usuario, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    private void loadData() {

        retrofitApi = RetrofitClient.getApiData();

        retrofitApi.getDefaultAutentication().enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    loginAdapter.update(response.body().getLogin());
                }

//                Login login = response.body().getLogin();
//                Log.d("login", String.valueOf(login));

                loadedData = true;
                Toast.makeText(SplashScreenActivity.this, "Acesso efetuado e dados gravados com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i("ERRO", t.getStackTrace().toString());
                Toast.makeText(SplashScreenActivity.this, "Erro ao acessar servidor: " + t.getStackTrace().toString(), Toast.LENGTH_LONG).show();
            }

        });
    }
    private void getApiData(){

        Login login = new Login();
        loginDAO = new LoginDAO(SplashScreenActivity.this);
        login = loginDAO.getAll();
        usuario = login.getUsuario();
        senha = login.getSenha();
    }


    private void createDB(){
        dbHelper = new DBHelper(this);
    }
}
