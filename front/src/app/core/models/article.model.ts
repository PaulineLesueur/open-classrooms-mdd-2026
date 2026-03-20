export interface ArticleResponse {
    id: number;
    title: string;
    content: string;
    authorUsername: string;
    themeName: string;
    createdAt: string;
}

export interface ArticleRequest {
    title: string;
    content: string;
    themeId: number;
    authorId: string;
}
