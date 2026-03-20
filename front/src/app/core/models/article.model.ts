/** Represents an article returned by the API. */
export interface ArticleResponse {
    id: number;
    title: string;
    content: string;
    authorUsername: string;
    themeName: string;
    createdAt: string;
}

/** Payload sent to the API to create a new article. */
export interface ArticleRequest {
    title: string;
    content: string;
    themeId: number;
    authorId: string;
}