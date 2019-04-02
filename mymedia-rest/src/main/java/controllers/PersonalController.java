package controllers;

import mymediaMain.enums.ResponseUtil;
import mymediaMain.response.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/personal")
public class PersonalController {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response defaultFunction(){
        return new Response(ResponseUtil.MSG_NOT_IMPLEMENTED, ResponseUtil.CODE_NOT_IMPLEMENTED);
    }
}
