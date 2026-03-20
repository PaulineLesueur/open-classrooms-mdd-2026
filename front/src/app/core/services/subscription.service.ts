import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TopicResponse } from '../models/topic.model';

@Injectable({
    providedIn: 'root'
})
export class SubscriptionService {

    private apiUrl = `${environment.apiUrl}/api/subscriptions`;

    constructor(private http: HttpClient) {}

    getUserSubscriptions(): Observable<TopicResponse[]> {
        return this.http.get<TopicResponse[]>(this.apiUrl);
    }

    subscribe(topicId: number): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}/${topicId}`, {});
    }

    unsubscribe(topicId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${topicId}`);
    }
}
