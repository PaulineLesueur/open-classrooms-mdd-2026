import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TopicResponse } from '../models/topic.model';

@Injectable({
    providedIn: 'root'
})
export class TopicService {

    private apiUrl = `${environment.apiUrl}/api/topics`;

    constructor(private http: HttpClient) {}

    getAll(): Observable<TopicResponse[]> {
        return this.http.get<TopicResponse[]>(this.apiUrl);
    }

    getById(id: number): Observable<TopicResponse> {
        return this.http.get<TopicResponse>(`${this.apiUrl}/${id}`);
    }
}
