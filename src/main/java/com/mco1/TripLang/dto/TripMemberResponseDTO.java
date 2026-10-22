package com.mco1.TripLang.dto;

import java.time.Instant;

/**
 * Contains trip membership information returned by the API.
 */
public class TripMemberResponseDTO {

    private final Long id;
    private final Long tripId;
    private final Long userId;
    private final String displayName;
    private final Instant joinedAt;

    /**
     * Creates a membership response.
     * Precondition: values come from an authorized membership lookup.
     * Postcondition: fields contain the supplied membership information.
     *
     * @param id the membership identifier
     * @param tripId the trip identifier
     * @param userId the member's user identifier
     * @param displayName the member's display name
     * @param joinedAt the membership creation timestamp
     */
    public TripMemberResponseDTO(
            Long id,
            Long tripId,
            Long userId,
            String displayName,
            Instant joinedAt
    ) {
        this.id = id;
        this.tripId = tripId;
        this.userId = userId;
        this.displayName = displayName;
        this.joinedAt = joinedAt;
    }

    /**
     * Gets the membership identifier.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the membership identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the trip identifier.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the trip identifier
     */
    public Long getTripId() {
        return tripId;
    }

    /**
     * Gets the member's user identifier.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the user identifier
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Gets the member's display name.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the membership creation timestamp.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the joining timestamp
     */
    public Instant getJoinedAt() {
        return joinedAt;
    }
}