package controllers;

import mymediaMain.dto.FollowWithTokenDto;
import mymediaMain.dto.FollowWithUserIdDto;
import mymediaMain.response.Response;
import mymediaMain.services.FollowingService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/follow")
public class FollowController {
    private FollowingService followingService = new FollowingService();

    @POST
    @Path("/withUserId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response followWithUserId(FollowWithUserIdDto followWithUserIdDto){
        return followingService.followWithUserId(followWithUserIdDto.getFollowerUserId(), followWithUserIdDto.getDestUserId());
    }

    @POST
    @Path("/withToken")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response followWithToken(FollowWithTokenDto followWithTokenDto){
        return followingService.followWithToken(followWithTokenDto.getToken(), followWithTokenDto.getDestUserId());
    }
}
