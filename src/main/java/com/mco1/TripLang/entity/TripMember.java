package com.mco1.TripLang.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

/**
 * Represents a user's membership in a trip.
 *
 * Each user may belong to a particular trip only once.
 * Leadership is stored separately on Trip.
 */
@Entity
@Table(
        name = "trip_members",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_trip_member_trip_user",
                        columnNames = {"trip_id", "user_id"}
                )
        }
)
public class TripMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", nullable = false, updatable = false)
    private Trip trip;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private Instant joinedAt;

    /**
     * Creates an empty entity for JPA.
     * Precondition: none.
     * Postcondition: an entity instance exists.
     */
    protected TripMember() {
    }

    /**
     * Creates a membership between a trip and a user.
     * Precondition: trip and user are nonnull; the service has
     * verified membership eligibility and authorization.
     * Postcondition: the membership references the supplied trip
     * and user.
     *
     * @param trip the trip being joined
     * @param user the user joining the trip
     * @throws IllegalArgumentException if either argument is null
     */
    public TripMember(Trip trip, User user) {
        if (trip == null) {
            throw new IllegalArgumentException("Trip is required");
        }

        if (user == null) {
            throw new IllegalArgumentException("User is required");
        }

        this.trip = trip;
        this.user = user;
    }

    /**
     * Records when the membership is first persisted.
     * Precondition: the entity is about to be inserted.
     * Postcondition: joinedAt contains the current instant.
     */
    @PrePersist
    private void onCreate() {
        joinedAt = Instant.now();
    }

    /**
     * Gets the membership identifier.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the identifier, or null before persistence
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the associated trip.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the trip
     */
    public Trip getTrip() {
        return trip;
    }

    /**
     * Gets the member.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the membership creation timestamp.
     * Precondition: none.
     * Postcondition: the entity is unchanged.
     *
     * @return the timestamp, or null before persistence
     */
    public Instant getJoinedAt() {
        return joinedAt;
    }
}
