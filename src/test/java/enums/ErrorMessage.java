package enums;

public enum ErrorMessage {
    WRONG_CREDENTIALS("Epic sadface: Username and password do not match any user in this service"),
    USERNAME_REQUIRED("Epic sadface: Username is required"),
    PASSWORD_REQUIRED("Epic sadface: Password is required"),
    LOCKED_OUT("Epic sadface: Sorry, this user has been locked out."),
    FIRST_NAME_REQUIRED("Error: First Name is required"),
    LAST_NAME_REQUIRED("Error: Last Name is required"),
    POSTAL_CODE_REQUIRED("Error: Postal Code is required");

    ErrorMessage(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessage() {
        return message;
    }
}
