export interface CommentResponse {
    id: number;
    body: string;
    authorUsername: string;
    createdAt: string;
}

export interface CommentRequest {
    body: string;
    authorId: string;
    articleId: number;
}
