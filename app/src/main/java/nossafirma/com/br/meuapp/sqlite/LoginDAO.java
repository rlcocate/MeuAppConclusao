package nossafirma.com.br.meuapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import nossafirma.com.br.meuapp.model.Login;
import nossafirma.com.br.meuapp.sqlite.DBHelper;

public class LoginDAO {

    private DBHelper dbHelper;
    private Login login;

    public static final String TABELA_LOGIN = "Login";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_USER = "user";
    public static final String COLUNA_FBLOGIN = "fblogin";
    public static final String COLUNA_PASS = "pass";

    public LoginDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public Login getBy(String usuario) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String colunas[] = {
                COLUNA_USER,
                COLUNA_FBLOGIN,
                COLUNA_PASS
        };
        String where = "user = '" + usuario + "'";
        Cursor cursor = db.query(true, TABELA_LOGIN, colunas, where,
                null, null, null, null, null);
        login = null;
        if (cursor != null) {
            cursor.moveToFirst();
            login = new Login();
            login.setUsuario(cursor.getString(cursor.getColumnIndex(COLUNA_USER)));
            login.setFblogin(cursor.getString(cursor.getColumnIndex(COLUNA_FBLOGIN)));
            login.setSenha(cursor.getString(cursor.getColumnIndex(COLUNA_PASS)));
        }
        return login;
    }

    public String add(Login login) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_USER, login.getUsuario());
        if (login.getFblogin() != null) {
            values.put(COLUNA_FBLOGIN, login.getFblogin());
        } else {
            values.put(COLUNA_PASS, login.getSenha());
        }

        long resultado = db.insert(TABELA_LOGIN, null, values);

        db.close();

        if (resultado == -1) {
            return "Erro ao inserir registro...";
        } else {
            return "Gravação efetuada com sucesso!";
        }
    }

    public Login getAll() {

        List<Login> logins = new LinkedList<>();
        Login login = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TABELA_LOGIN;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                login = new Login();
                login.setUsuario(cursor.getString(cursor.getColumnIndex(COLUNA_USER)));
                if (cursor.getString(cursor.getColumnIndex(COLUNA_FBLOGIN)) != null) {
                    login.setFblogin(cursor.getString(cursor.getColumnIndex(COLUNA_FBLOGIN)));
                } else {
                    login.setSenha(cursor.getString(cursor.getColumnIndex(COLUNA_PASS)));
                }
                logins.add(login);
            } while (cursor.moveToNext());
        }
        return login;
    }

}