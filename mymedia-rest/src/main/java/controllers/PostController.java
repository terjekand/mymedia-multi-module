package controllers;

import mymediaMain.dto.*;
import mymediaMain.response.LikersResponse;
import mymediaMain.response.PostListResponse;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getpostsofuserbyusername/{username}")
    public PostListResponse getPostsOfUserByUsername(@PathParam("username") String username) {
        return postService.getPostsOfUserByUsername(username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getpostsofuserbytoken/{token}")
    public PostListResponse getPostsOfUserByToken(@PathParam("token") String token) {
        return postService.getPostsOfUserByToken(token);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getpostsofuserbyuserid/{userId}")
    public PostListResponse getPostsOfUserByUserId(@PathParam("userId") String userId) {
        return postService.getPostsOfUserByUserId(userId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deletepostbyid/{postId}")
    public Response deletePostById(@PathParam("postId") String postId) {
        return postService.deletePostById(postId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deletepostbytoken")
    public Response deletePostOfUserByToken(DeletePostWithTokenDto deletePostWithTokenDto) {
        return deletePostOfUserByToken(deletePostWithTokenDto);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createpost")
    public PostResponse createPost(CreatePostDto createPostDto){
        return postService.createPost(createPostDto);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createpostwithtoken")
    public PostResponse createPost(CreatePostWithTokenDto createPostWithTokenDto){
        return postService.createPostWithToken(createPostWithTokenDto);
    }
}
