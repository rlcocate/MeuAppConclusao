package nossafirma.com.br.meuapp.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("login")
    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
