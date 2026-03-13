export interface UserResponse {
    id: string;
    username: string;
    email: string;
}

export interface UserRequest {
    username: string;
    email: string;
}

export interface UserPasswordRequest {
    password: string;
}