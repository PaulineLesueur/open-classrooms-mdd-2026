import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CommentRequest, CommentResponse } from '../models/comment.model';

@Injectable({
    providedIn: 'root'
})
export class CommentService {

    private baseUrl = environment.apiUrl;

    constructor(private http: HttpClient) {}

    getByPost(postId: number): Observable<CommentResponse[]> {
        return this.http.get<CommentResponse[]>(`${this.baseUrl}/api/post/${postId}/comments`);
    }

    create(request: CommentRequest): Observable<CommentResponse> {
        return this.http.post<CommentResponse>(`${this.baseUrl}/api/comments`, request);
    }
}
