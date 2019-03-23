package controllers;

import database.entities.User;
import mymediaMain.dto.LikeUserIdDto;
import mymediaMain.dto.LikeWithTokenDto;
import mymediaMain.response.Response;
import mymediaMain.services.PostService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/post")
public class PostController {

    PostService postService;


    @GET
    @Path("/getlikersofpost/{postid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getLikersOfPost(@PathParam("postid") Long postId) {
        return postService.getLikersOfPost(postId);
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
}
