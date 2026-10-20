package com.mco1.TripLang.dto;

import com.mco1.TripLang.entity.AccountStatus;
import com.mco1.TripLang.entity.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class UserDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String displayName;

    @NotBlank
    @Email
    @Size(max = 254)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    private Instant createdAt;

    private Instant updatedAt;

    /**
     * Creates a user response.
     * Precondition: values come from an authorized user lookup.
     * Postcondition: the DTO contains the supplied user information.
     *
     * @param id the user identifier
     * @param displayName the display name
     * @param email the email address
     * @param role the application role
     * @param status the account status
     * @param createdAt the account creation timestamp
     * @param updatedAt the last modification timestamp
     */
    public UserDTO(
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
     * Postcondition: the DTO is unchanged.
     *
     * @return the user identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the display name.
     * Precondition: none.
     * Postcondition: the DTO is unchanged.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the email address.
     * Precondition: none.
     * Postcondition: the DTO is unchanged.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the application role.
     * Precondition: none.
     * Postcondition: the DTO is unchanged.
     *
     * @return the application role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Gets the account status.
     * Precondition: none.
     * Postcondition: the DTO is unchanged.
     *
     * @return the account status
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * Gets the creation timestamp.
     * Precondition: none.
     * Postcondition: the DTO is unchanged.
     *
     * @return the creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the last modification timestamp.
     * Precondition: none.
     * Postcondition: the DTO is unchanged.
     *
     * @return the last modification timestamp
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}