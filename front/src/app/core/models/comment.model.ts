/** Represents a comment returned by the API. */
export interface CommentResponse {
    id: number;
    body: string;
    authorUsername: string;
    createdAt: string;
}

/** Payload sent to the API to create a new comment. */
export interface CommentRequest {
    body: string;
    authorId: string;
    postId: number;
}
