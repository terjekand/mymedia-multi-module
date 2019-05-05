package controllers;

import mymediaMain.dto.FollowWithTokenDto;
import mymediaMain.dto.FollowWithUserIdDto;
import mymediaMain.response.Response;
import mymediaMain.services.FollowService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/follow")
public class FollowController {
    private FollowService followService = new FollowService();

    @POST
    @Path("/withUserId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response followWithUserId(FollowWithUserIdDto followWithUserIdDto){
        return followService.followWithUserId(followWithUserIdDto.getFollowerUserId(), followWithUserIdDto.getDestUserId());
    }

    @POST
    @Path("/withToken")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response followWithToken(FollowWithTokenDto followWithTokenDto){
        return followService.followWithToken(followWithTokenDto.getToken(), followWithTokenDto.getDestUserId());
    }
}
