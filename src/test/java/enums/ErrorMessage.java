package enums;

public enum ErrorMessage {
    WRONG_CREDENTIALS("Epic sadface: Username and password do not match any user in this service"),
    USERNAME_REQUIRED("Epic sadface: Username is required"),
    PASSWORD_REQUIRED("Epic sadface: Password is required"),
    LOCKED_OUT("Epic sadface: Sorry, this user has been locked out.");

    ErrorMessage(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessage() {
        return message;
    }
}
