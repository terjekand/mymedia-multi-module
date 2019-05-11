package controllers;

import mymediaMain.response.PostListResponse;
import mymediaMain.services.NewsFeedService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/newsfeed")
public class NewsFeedController {

    NewsFeedService newsFeedService = new NewsFeedService();

    @GET
    @Path("/getbyuserid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PostListResponse getNewsFeedDataByUserId(@PathParam("id") String userId){
        return newsFeedService.fillNewsFeedByUserId(userId);
    }

    @GET
    @Path("/getbytoken/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public PostListResponse getNewsFeedDataByToken(@PathParam("token") String token){
        return newsFeedService.fillNewsFeedByToken(token);
    }
}
