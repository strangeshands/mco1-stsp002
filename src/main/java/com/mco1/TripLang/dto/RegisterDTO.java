package com.mco1.TripLang.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Contains the information submitted during registration.
 */
public class RegisterDTO {

    @NotBlank(message = "Display name is required")
    @Size(max = 100, message = "Display name must not exceed 100 characters")
    private String displayName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 254, message = "Email must not exceed 254 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    /**
     * Creates an empty request for JSON binding.
     * Precondition: none.
     * Postcondition: an empty request exists.
     */
    public RegisterDTO() {
    }

    /**
     * Creates a registration request.
     * Precondition: none; fields require validation before processing.
     * Postcondition: the request contains the supplied values.
     *
     * @param displayName the submitted display name
     * @param email the submitted email address
     * @param password the submitted plain-text password
     */
    public RegisterDTO(
            String displayName,
            String email,
            String password
    ) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the display name.
     * Precondition: none.
     * Postcondition: the request is unchanged.
     *
     * @return the submitted display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name.
     * Precondition: none; validation occurs before processing.
     * Postcondition: the field contains the supplied value.
     *
     * @param displayName the submitted display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
     * Gets the password for encoding during registration.
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