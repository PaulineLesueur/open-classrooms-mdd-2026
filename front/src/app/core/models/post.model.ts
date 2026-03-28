/** Represents a post returned by the API. */
export interface PostResponse {
    id: number;
    title: string;
    content: string;
    authorUsername: string;
    topicName: string;
    createdAt: string;
}

/** Payload sent to the API to create a new post. */
export interface PostRequest {
    title: string;
    content: string;
    topicId: number;
}
