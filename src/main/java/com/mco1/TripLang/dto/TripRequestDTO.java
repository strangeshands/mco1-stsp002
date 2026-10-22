package com.mco1.TripLang.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Contains editable trip details for creation and full updates.
 */
public class TripRequestDTO {

    @NotBlank(message = "Trip name is required")
    @Size(max = 100, message = "Trip name must not exceed 100 characters")
    private String tripName;

    @Size(max = 200, message = "Description must not exceed 200 characters")
    private String tripDescription;

    private LocalDate startDate;

    private LocalDate endDate;

    /**
     * Creates an empty request for JSON binding.
     * Precondition: none.
     * Postcondition: an empty request exists.
     */
    public TripRequestDTO() {
    }

    /**
     * Creates a request with the supplied trip details.
     * Precondition: none; validation is required before processing.
     * Postcondition: fields contain the supplied values.
     *
     * @param tripName the trip name
     * @param tripDescription the optional description
     * @param startDate the optional start date
     * @param endDate the optional end date
     */
    public TripRequestDTO(
            String tripName,
            String tripDescription,
            LocalDate startDate,
            LocalDate endDate
    ) {
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Checks the date range when both dates are supplied.
     * Precondition: none.
     * Postcondition: the request is unchanged.
     *
     * @return true if a date is absent or the end is not before the start
     */
    @AssertTrue(message = "End date must be on or after start date")
    public boolean isDateRangeValid() {
        return startDate == null
                || endDate == null
                || !endDate.isBefore(startDate);
    }

    /**
     * Gets the trip name.
     * Precondition: none.
     * Postcondition: the request is unchanged.
     *
     * @return the trip name
     */
    public String getTripName() {
        return tripName;
    }

    /**
     * Sets the trip name.
     * Precondition: none; validation occurs before processing.
     * Postcondition: the field contains the supplied value.
     *
     * @param tripName the trip name
     */
    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    /**
     * Gets the description.
     * Precondition: none.
     * Postcondition: the request is unchanged.
     *
     * @return the optional description
     */
    public String getTripDescription() {
        return tripDescription;
    }

    /**
     * Sets the description.
     * Precondition: none; validation occurs before processing.
     * Postcondition: the field contains the supplied value.
     *
     * @param tripDescription the optional description
     */
    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    /**
     * Gets the start date.
     * Precondition: none.
     * Postcondition: the request is unchanged.
     *
     * @return the optional start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     * Precondition: none; validation occurs before processing.
     * Postcondition: the field contains the supplied value.
     *
     * @param startDate the optional start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     * Precondition: none.
     * Postcondition: the request is unchanged.
     *
     * @return the optional end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     * Precondition: none; validation occurs before processing.
     * Postcondition: the field contains the supplied value.
     *
     * @param endDate the optional end date
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}