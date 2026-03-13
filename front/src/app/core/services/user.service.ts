import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserRequest, UserPasswordRequest, UserResponse } from '../models/user.model';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private apiUrl = 'http://localhost:8080/api/users';

    constructor(private http: HttpClient) {}

    getById(id: string): Observable<UserResponse> {
        return this.http.get<UserResponse>(`${this.apiUrl}/${id}`);
    }

    update(id: string, request: UserRequest): Observable<UserResponse> {
        return this.http.put<UserResponse>(`${this.apiUrl}/${id}`, request);
    }

    updatePassword(id: string, request: UserPasswordRequest): Observable<UserResponse> {
        return this.http.put<UserResponse>(`${this.apiUrl}/${id}/password`, request);
    }
}