package com.mco1.TripLang.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private User user;

    @DecimalMin(value = "0.00")
    @Digits(integer = 17, fraction = 2)
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = new BigDecimal("0.00");

    @Column(nullable = false, length = 3, updatable = false)
    private String currency = "PHP";

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Version
    private Long version;

    /**
     * Creates an empty entity for JPA.
     * Precondition: none.
     * Postcondition: an entity instance exists.
     */
    protected Wallet() {
    }

    /**
     * Creates a zero-balance PHP wallet for a user.
     * Precondition: user is not null.
     * Postcondition: the wallet belongs to the supplied user.
     *
     * @param user the wallet owner
     * @throws IllegalArgumentException if user is null
     */
    public Wallet(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Wallet owner is required");
        }

        this.user = user;
    }

    /**
     * Sets timestamps before the first database insert.
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
     * Updates the modification timestamp before a database update.
     * Precondition: the persisted entity is being updated.
     * Postcondition: updatedAt contains the current instant.
     */
    @PreUpdate
    private void onUpdate() {
        updatedAt = Instant.now();
    }

    /**
     * Gets the wallet identifier.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the identifier, or null before persistence
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the wallet owner.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the owner
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the current balance.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the wallet balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the balance after a validated financial operation.
     * Precondition: balance is nonnegative and has at most two
     * fractional digits and seventeen integer digits.
     * Postcondition: the balance equals the supplied amount.
     *
     * @param balance the new balance
     * @throws IllegalArgumentException if the amount is invalid
     */
    public void setBalance(BigDecimal balance) {
        if (balance == null
                || balance.signum() < 0
                || balance.scale() > 2
                || balance.compareTo(
                new BigDecimal("99999999999999999.99")) > 0) {
            throw new IllegalArgumentException(
                    "Balance must be nonnegative, fit DECIMAL(19,2), "
                            + "and have at most two decimal places"
            );
        }

        this.balance = balance.setScale(2);
    }

    /**
     * Gets the currency.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return PHP
     */
    public String getCurrency() {
        return currency;
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