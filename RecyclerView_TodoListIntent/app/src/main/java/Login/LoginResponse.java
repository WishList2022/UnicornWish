package Login;

public class LoginResponse {

    private String account_id;
    private String password;

    public LoginResponse(String account_id, String password) {
        this.account_id = account_id;
        this.password = password;
    }

    public String getAccount_id() {
        return account_id;
    }

    public String getPassword() {
        return password;
    }

}
