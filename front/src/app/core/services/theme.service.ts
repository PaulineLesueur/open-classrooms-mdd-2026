import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ThemeResponse } from '../models/theme.model';

@Injectable({
    providedIn: 'root'
})
export class ThemeService {

    private apiUrl = `${environment.apiUrl}/api/themes`;

    constructor(private http: HttpClient) {}

    getAll(): Observable<ThemeResponse[]> {
        return this.http.get<ThemeResponse[]>(this.apiUrl);
    }

    getById(id: number): Observable<ThemeResponse> {
        return this.http.get<ThemeResponse>(`${this.apiUrl}/${id}`);
    }
}
