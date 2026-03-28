import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PostRequest, PostResponse } from '../models/post.model';

@Injectable({
    providedIn: 'root'
})
export class PostService {

    private apiUrl = `${environment.apiUrl}/api/posts`;

    constructor(private http: HttpClient) {}

    getAll(): Observable<PostResponse[]> {
        return this.http.get<PostResponse[]>(this.apiUrl);
    }

    getById(id: number): Observable<PostResponse> {
        return this.http.get<PostResponse>(`${this.apiUrl}/${id}`);
    }

    create(request: PostRequest): Observable<PostResponse> {
        return this.http.post<PostResponse>(this.apiUrl, request);
    }
}
