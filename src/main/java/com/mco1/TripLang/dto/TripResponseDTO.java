package com.mco1.TripLang.dto;

import com.mco1.TripLang.entity.TripStatus;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Contains trip information returned by the API.
 */
public class TripResponseDTO {

    private final Long id;
    private final String tripName;
    private final String tripDescription;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Long leaderId;
    private final String leaderDisplayName;
    private final TripStatus status;
    private final Instant createdAt;
    private final Instant updatedAt;

    /**
     * Creates a trip response.
     * Precondition: values come from an authorized trip lookup.
     * Postcondition: fields contain the supplied trip information.
     *
     * @param id the trip identifier
     * @param tripName the trip name
     * @param tripDescription the optional description
     * @param startDate the optional start date
     * @param endDate the optional end date
     * @param leaderId the leader's user identifier
     * @param leaderDisplayName the leader's display name
     * @param status the trip status
     * @param createdAt the creation timestamp
     * @param updatedAt the last modification timestamp
     */
    public TripResponseDTO(
            Long id,
            String tripName,
            String tripDescription,
            LocalDate startDate,
            LocalDate endDate,
            Long leaderId,
            String leaderDisplayName,
            TripStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaderId = leaderId;
        this.leaderDisplayName = leaderDisplayName;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the trip identifier.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the trip identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the trip name.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the trip name
     */
    public String getTripName() {
        return tripName;
    }

    /**
     * Gets the description.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the optional description
     */
    public String getTripDescription() {
        return tripDescription;
    }

    /**
     * Gets the start date.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the optional start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the optional end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Gets the leader's identifier.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the leader's user identifier
     */
    public Long getLeaderId() {
        return leaderId;
    }

    /**
     * Gets the leader's display name.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the leader's display name
     */
    public String getLeaderDisplayName() {
        return leaderDisplayName;
    }

    /**
     * Gets the trip status.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the trip status
     */
    public TripStatus getStatus() {
        return status;
    }

    /**
     * Gets the creation timestamp.
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