package com.mco1.TripLang.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Represents a trip with a leader and lifecycle status.
 *
 * Memberships and expenses reference this entity through
 * their own relationships.
 */
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "trip_name", nullable = false, length = 100)
    private String tripName;

    @Size(max = 200)
    @Column(name = "trip_description", length = 200)
    private String tripDescription;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leader_id", nullable = false)
    private User leader;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TripStatus status = TripStatus.ACTIVE;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Creates an empty entity for JPA.
     * Precondition: none.
     * Postcondition: an entity instance exists.
     */
    protected Trip() {
    }

    /**
     * Creates an active trip.
     * Precondition: arguments satisfy the entity's validation rules;
     * the leader is an active user.
     * Postcondition: an active trip exists with the supplied details.
     *
     * @param tripName the trip name
     * @param tripDescription optional trip description
     * @param startDate optional start date
     * @param endDate optional end date
     * @param leader the trip leader
     */
    public Trip(
            String tripName,
            String tripDescription,
            LocalDate startDate,
            LocalDate endDate,
            User leader
    ) {
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leader = leader;
    }

    /**
     * Initializes audit timestamps before insertion.
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
     * Validates the date range when both dates are provided.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return true if either date is absent or the range is valid
     */
    @AssertTrue(message = "End date must be on or after start date")
    public boolean isDateRangeValid() {
        return startDate == null
                || endDate == null
                || !endDate.isBefore(startDate);
    }

    /**
     * Gets the trip identifier.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the identifier, or null before persistence
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the trip name.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the trip name
     */
    public String getTripName() {
        return tripName;
    }

    /**
     * Updates the trip name.
     * Precondition: the name is nonblank and at most 100 characters.
     * Postcondition: the trip name equals the supplied value.
     *
     * @param tripName the validated trip name
     */
    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    /**
     * Gets the trip description.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the description, which may be null
     */
    public String getTripDescription() {
        return tripDescription;
    }

    /**
     * Updates the trip description.
     * Precondition: the description is null or at most 200 characters.
     * Postcondition: the description equals the supplied value.
     *
     * @param tripDescription the optional description
     */
    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    /**
     * Gets the start date.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the start date, which may be null
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Updates the start date.
     * Precondition: the final date range is validated before saving.
     * Postcondition: the start date equals the supplied value.
     *
     * @param startDate the optional start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the end date, which may be null
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Updates the end date.
     * Precondition: the final date range is validated before saving.
     * Postcondition: the end date equals the supplied value.
     *
     * @param endDate the optional end date
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the trip leader.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the leader
     */
    public User getLeader() {
        return leader;
    }

    /**
     * Changes the trip leader.
     * Precondition: an authorized service has verified that the
     * supplied user is an active member of this trip.
     * Postcondition: the supplied user becomes the leader.
     *
     * @param leader the new leader
     * @throws IllegalArgumentException if leader is null
     */
    public void setLeader(User leader) {
        if (leader == null) {
            throw new IllegalArgumentException("Trip leader is required");
        }

        this.leader = leader;
    }

    /**
     * Gets the trip status.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the status
     */
    public TripStatus getStatus() {
        return status;
    }

    /**
     * Updates the trip status.
     * Precondition: an authorized service has validated the transition.
     * Postcondition: the trip has the supplied status.
     *
     * @param status the new status
     * @throws IllegalArgumentException if status is null
     */
    public void setStatus(TripStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Trip status is required");
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