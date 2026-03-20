import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ArticleRequest, ArticleResponse } from '../models/article.model';

@Injectable({
    providedIn: 'root'
})
export class ArticleService {

    private apiUrl = `${environment.apiUrl}/api/articles`;

    constructor(private http: HttpClient) {}

    getAll(): Observable<ArticleResponse[]> {
        return this.http.get<ArticleResponse[]>(this.apiUrl);
    }

    getById(id: number): Observable<ArticleResponse> {
        return this.http.get<ArticleResponse>(`${this.apiUrl}/${id}`);
    }

    create(request: ArticleRequest): Observable<ArticleResponse> {
        return this.http.post<ArticleResponse>(this.apiUrl, request);
    }
}
