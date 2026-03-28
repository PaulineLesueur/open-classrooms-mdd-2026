import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthResponse, LoginRequest, RegisterRequest } from "../models/auth.model";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiUrl = `${environment.apiUrl}/api/auth`;

    constructor(private http: HttpClient) { }

    register(request: RegisterRequest): Observable<AuthResponse> {
        return this.http.post<AuthResponse>(`${this.apiUrl}/register`, request);
    }

    login(request: LoginRequest): Observable<AuthResponse> {
        return this.http.post<AuthResponse>(`${this.apiUrl}/login`, request);
    }

    saveToken(token: string): void {
        localStorage.setItem('token', token);
    }

    saveUser(response: AuthResponse): void {
        localStorage.setItem('token', response.token);
        localStorage.setItem('userId', response.id);
        localStorage.setItem('username', response.username);
        localStorage.setItem('email', response.email);
    }

    getUserId(): string | null {
        return localStorage.getItem('userId');
    }

    getUsername(): string | null {
        return localStorage.getItem('username');
    }

    getEmail(): string | null {
        return localStorage.getItem('email');
    }

    getToken(): string | null {
        return localStorage.getItem('token');
    }

    logout(): void {
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        localStorage.removeItem('username');
        localStorage.removeItem('email');
    }

    isLoggedIn(): boolean {
        return this.getToken() !== null;
    }
}