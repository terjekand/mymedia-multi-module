package controllers;

import database.dao.DataAdapter;
import mymediaMain.dto.AuthDto;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import mymediaMain.services.AuthService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class MymRestAuth {
    AuthService authService = new AuthService();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthDto authDto){
        return authService.login(authDto);
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(String token){
        return authService.logout(token);
    }

    @GET
    @Path("/connection")
    @Produces(MediaType.APPLICATION_JSON)
    public Response connection(){
        DataAdapter dataAdapter = new DataAdapter();
        dataAdapter.connect();
        dataAdapter.saveUser(null);
        return new Response(ErrorMessages.OK, ErrorCodes.OK);
    }

    @GET
    @Path("/disconnection")
    @Produces(MediaType.APPLICATION_JSON)
    public Response disconnect(){
        DataAdapter dataAdapter = new DataAdapter();
        dataAdapter.disconnect();
        return new Response(ErrorMessages.OK, ErrorCodes.OK);
    }

}
