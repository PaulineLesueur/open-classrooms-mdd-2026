import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ThemeResponse } from '../models/theme.model';

@Injectable({
    providedIn: 'root'
})
export class SubscriptionService {

    private apiUrl = `${environment.apiUrl}/api/subscriptions`;

    constructor(private http: HttpClient) {}

    getUserSubscriptions(): Observable<ThemeResponse[]> {
        return this.http.get<ThemeResponse[]>(this.apiUrl);
    }

    subscribe(themeId: number): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}/${themeId}`, {});
    }

    unsubscribe(themeId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${themeId}`);
    }
}
