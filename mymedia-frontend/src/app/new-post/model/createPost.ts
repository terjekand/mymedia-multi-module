export interface CreatePostDto {
  token: string;
  text: string;
}

export interface CreatePostResponse {
  errorCode: number;
  errorMessage: string;
  postId: string;
}
