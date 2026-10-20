package com.mco1.TripLang.dto;

import com.mco1.TripLang.entity.TripStatus;
import com.mco1.TripLang.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

public class TripDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String tripName;

    @Size(max = 200)
    private String tripDescription;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotNull
    private User leader;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TripStatus status = TripStatus.ACTIVE;

    private Instant createdAt;

    private Instant updatedAt;

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
    public TripDTO(
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