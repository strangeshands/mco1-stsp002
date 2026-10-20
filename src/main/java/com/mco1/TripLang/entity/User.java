package com.mco1.TripLang.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_name", nullable = false, length = 100)
    private String displayName;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 10)
    private UserRole role = UserRole.USER;

    @Column(nullable = false, length = 10)
    private AccountStatus status = AccountStatus.ACTIVE;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Creates an empty entity for JPA.
     * Precondition: none.
     * Postcondition: an entity instance exists.
     */
    protected User() {
    }

    /**
     * Creates an active regular user.
     * Precondition: arguments have been validated and the password
     * has been encoded by PasswordEncoder.
     * Postcondition: the user has USER role and ACTIVE status.
     *
     * @param displayName the user's display name
     * @param email the normalized email address
     * @param passwordHash the encoded password
     */
    public User(
            String displayName,
            String email,
            String passwordHash
    ) {
        this.displayName = displayName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    /**
     * Initializes timestamps before insertion.
     * Precondition: the entity is about to be persisted.
     * Postcondition: both timestamps contain the current instant.
     */
    @PrePersist
    private void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    /**
     * Refreshes the modification timestamp before an update.
     * Precondition: the persisted entity is being updated.
     * Postcondition: updatedAt contains the current instant.
     */
    @PreUpdate
    private void onUpdate() {
        updatedAt = Instant.now();
    }

    /**
     * Gets the identifier.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the identifier, or null before persistence
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the display name.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Updates the display name.
     * Precondition: the name is nonblank and at most 100 characters.
     * Postcondition: the display name equals the supplied value.
     *
     * @param displayName the validated display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the email address.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the encoded password for authentication.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Replaces the encoded password.
     * Precondition: the supplied value was produced by PasswordEncoder.
     * Postcondition: the stored hash equals the supplied value.
     *
     * @param passwordHash the new encoded password
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gets the application role.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Gets the account status.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the account status
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * Updates the account status.
     * Precondition: an authorized service supplies a nonnull status.
     * Postcondition: the account has the supplied status.
     *
     * @param status the new account status
     * @throws IllegalArgumentException if status is null
     */
    public void setStatus(AccountStatus status) {
        if (status == null) {
            throw new IllegalArgumentException(
                    "Account status is required"
            );
        }

        this.status = status;
    }

    /**
     * Gets the creation timestamp.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the timestamp, or null before persistence
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the last modification timestamp.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the timestamp, or null before persistence
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}