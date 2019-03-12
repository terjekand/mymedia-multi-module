package controllers;

import database.entities.User;
import mymediaMain.dto.AuthDto;
import mymediaMain.response.Response;
import mymediaMain.services.AuthService;
import mymediaMain.services.RegistrationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class MymRestAuth {
    private AuthService authService = new AuthService();
    private RegistrationService registrationService = new RegistrationService();

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

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registration(User user){
        return registrationService.userRegistration(user);
    }

}
