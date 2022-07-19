package Register;

public class RegisterRequest {
    private String account_id;
    private String password;
    private String username;

    public RegisterRequest(String account_id, String password, String username) {
        this.account_id = account_id;
        this.password = password;
        this.username = username;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

