package controllers;

import mymediaMain.dto.FollowWithTokenDto;
import mymediaMain.dto.FollowWithUserIdDto;
import mymediaMain.response.FollowAndFollowingResponse;
import mymediaMain.response.Response;
import mymediaMain.services.FollowService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/follow")
public class FollowController {
    private FollowService followService = new FollowService();

    @POST
    @Path("/byuserid")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response followWithUserId(FollowWithUserIdDto followWithUserIdDto){
        return followService.followWithUserId(followWithUserIdDto.getFollowerUserId(), followWithUserIdDto.getDestUserId());
    }

    @POST
    @Path("/bytoken")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response followWithToken(FollowWithTokenDto followWithTokenDto){
        return followService.followWithToken(followWithTokenDto.getToken(), followWithTokenDto.getDestUserId());
    }



    @GET
    @Path("/getnumbersbytoken/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public FollowAndFollowingResponse getFollowersAndFollowingByToken(@PathParam("token") String token) {
        return followService.getFollowersAndFollowingByToken(token);
    }

    @GET
    @Path("/getnumbersbyuserid/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public FollowAndFollowingResponse getFollowersAndFollowing(@PathParam("userId") String userId) {
        return followService.getFollowersAndFollowing(userId);
    }
}
