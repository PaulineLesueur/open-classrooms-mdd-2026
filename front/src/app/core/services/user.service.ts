import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserRequest, UserPasswordRequest, UserResponse } from '../models/user.model';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private apiUrl = `${environment.apiUrl}/api/users`;

    constructor(private http: HttpClient) {}

    getById(id: string): Observable<UserResponse> {
        return this.http.get<UserResponse>(`${this.apiUrl}/${id}`);
    }

    update(request: UserRequest): Observable<UserResponse> {
        return this.http.put<UserResponse>(`${this.apiUrl}/me`, request);
    }

    updatePassword(request: UserPasswordRequest): Observable<UserResponse> {
        return this.http.put<UserResponse>(`${this.apiUrl}/me/password`, request);
    }
}