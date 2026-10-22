package com.mco1.TripLang.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Contains the credentials submitted during login.
 */
public class LogInDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 254, message = "Email must not exceed 254 characters")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    /**
     * Creates an empty request for JSON binding.
     * Precondition: none.
     * Postcondition: an empty request exists.
     */
    public LogInDTO() {
    }

    /**
     * Creates a login request.
     * Precondition: none; fields require validation before processing.
     * Postcondition: the request contains the supplied credentials.
     *
     * @param email the submitted email address
     * @param password the submitted plain-text password
     */
    public LogInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the email address.
     * Precondition: none.
     * Postcondition: the request is unchanged.
     *
     * @return the submitted email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     * Precondition: none; validation occurs before processing.
     * Postcondition: the field contains the supplied value.
     *
     * @param email the submitted email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password for authentication.
     * Precondition: none.
     * Postcondition: the request is unchanged.
     *
     * @return the submitted plain-text password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * Precondition: none; validation occurs before processing.
     * Postcondition: the field contains the supplied value.
     *
     * @param password the submitted plain-text password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}