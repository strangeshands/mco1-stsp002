package com.mco1.TripLang.dto;

import com.mco1.TripLang.entity.AccountStatus;
import com.mco1.TripLang.entity.UserRole;

import java.time.Instant;

/**
 * Contains user information safe to return through the API.
 * Passwords and password hashes are excluded.
 */
public class UserResponseDTO {

    private final Long id;
    private final String displayName;
    private final String email;
    private final UserRole role;
    private final AccountStatus status;
    private final Instant createdAt;
    private final Instant updatedAt;

    /**
     * Creates a user response.
     * Precondition: values come from an authorized user lookup.
     * Postcondition: the response contains the supplied user information.
     *
     * @param id the user identifier
     * @param displayName the display name
     * @param email the email address
     * @param role the application role
     * @param status the account status
     * @param createdAt the account creation timestamp
     * @param updatedAt the last modification timestamp
     */
    public UserResponseDTO(
            Long id,
            String displayName,
            String email,
            UserRole role,
            AccountStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the user identifier.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the user identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the display name.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the email address.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the application role.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the application role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Gets the account status.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the account status
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * Gets the account creation timestamp.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the last modification timestamp.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the last modification timestamp
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}