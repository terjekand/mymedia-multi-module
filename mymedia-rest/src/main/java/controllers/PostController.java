package controllers;

import database.entities.Post;
import database.entities.User;
import mymediaMain.dto.CreatePostDto;
import mymediaMain.dto.LikeUserIdDto;
import mymediaMain.dto.LikeWithTokenDto;
import mymediaMain.response.LikersResponse;
import mymediaMain.response.PostResponse;
import mymediaMain.response.Response;
import mymediaMain.services.PostService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/post")
public class PostController {

    PostService postService = new PostService();


    @GET
    @Path("/getlikersofpost/{postid}")
    @Produces(MediaType.APPLICATION_JSON)
    public LikersResponse getLikersOfPost(@PathParam("postid") String postId) {
        LikersResponse response = postService.getLikersOfPost(postId);
        return response;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/likewithuserid")
    public Response likeWithUserId(LikeUserIdDto likeUserIdDto) {
        return postService.likeWithUserId(likeUserIdDto.getUserId(), likeUserIdDto.getPostId());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/likewithtoken")
    public Response likeWithToken(LikeWithTokenDto likeWithTokenDto) {
        return postService.likeWithToken(likeWithTokenDto.getToken(), likeWithTokenDto.getPostId());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createpost")
    public PostResponse createPost(CreatePostDto createPostDto){
        return postService.createPost(createPostDto);
    }
}
