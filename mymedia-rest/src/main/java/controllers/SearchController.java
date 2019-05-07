package controllers;

import mymediaMain.dto.SearchDto;
import mymediaMain.response.SearchResponse;
import mymediaMain.services.SearchService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/search")
public class SearchController {
    SearchService searchService = new SearchService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/byuserid")
    public SearchResponse multiSearch(SearchDto searchDto){
        return searchService.searchUser(searchDto);
    }
}
