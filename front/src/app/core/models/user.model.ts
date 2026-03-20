/** Represents a user's public profile returned by the API. */
export interface UserResponse {
    id: string;
    username: string;
    email: string;
}

/** Payload sent to the API to update a user's profile (username and email). */
export interface UserRequest {
    username: string;
    email: string;
}

/** Payload sent to the API to update a user's password. */
export interface UserPasswordRequest {
    password: string;
}
