package controllers;

import database.entities.User;
import mymediaMain.dto.SearchDto;
import mymediaMain.response.SearchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/search")
public class SearchController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResponse multiSearch(SearchDto searchDto){
        return null;
    }
}
