package com.mco1.TripLang.dto;

/**
 * Contains the authentication token and authenticated user information.
 */
public class AuthResponseDTO {

    private final String accessToken;
    private final String tokenType;
    private final long expiresIn;
    private final UserResponseDTO user;

    /**
     * Creates an authentication response using a Bearer token.
     * Precondition: authentication succeeded; accessToken and user
     * are nonnull, and expiresIn is positive.
     * Postcondition: the response contains the supplied token,
     * expiration duration, and user information.
     *
     * @param accessToken the generated JWT
     * @param expiresIn the token lifetime in seconds
     * @param user the authenticated user's safe profile
     */
    public AuthResponseDTO(
            String accessToken,
            long expiresIn,
            UserResponseDTO user
    ) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.expiresIn = expiresIn;
        this.user = user;
    }

    /**
     * Gets the access token.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the generated JWT
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Gets the authentication scheme.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return Bearer
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Gets the token lifetime.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the token lifetime in seconds
     */
    public long getExpiresIn() {
        return expiresIn;
    }

    /**
     * Gets the authenticated user's profile.
     * Precondition: none.
     * Postcondition: the response is unchanged.
     *
     * @return the safe user profile
     */
    public UserResponseDTO getUser() {
        return user;
    }
}