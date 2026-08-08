package user;

public class User {
    private final String login;
    private final String password;

    public User(String login, String password) {
        this.password = password;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
