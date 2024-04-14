package com.example.library.DTO;

/**
 * Data Transfer Object for login form.
 */
public class LoginForm {

    private String login;
    private String password;

    /**
     * Gets the login of the user.
     *
     * @return the login of the user
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login of the user.
     *
     * @param login the login of the user
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
