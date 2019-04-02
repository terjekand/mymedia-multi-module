package controllers;

import mymediaMain.dto.SearchDto;
import mymediaMain.response.SearchResponse;
import mymediaMain.services.SearchService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/search")
public class SearchController {
    SearchService searchService = new SearchService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{req}")
    public SearchResponse multiSearch(@PathParam("req") String req){
        return searchService.searchUser(req);
    }
}
