/** Payload sent to the API to register a new user. */
export interface RegisterRequest {
    username: string;
    email: string;
    password: string;
}

/** Payload sent to the API to authenticate a user. The identifier can be an email or a username. */
export interface LoginRequest {
    identifier: string;
    password: string;
}

/** Response returned by the API after a successful login or registration. Contains the JWT token and basic user info. */
export interface AuthResponse {
    token: string;
    id: string;
    username: string;
    email: string;
}
