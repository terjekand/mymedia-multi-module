package controllers;

import mymediaMain.dto.LoginDto;
import mymediaMain.dto.RegistrationDto;
import mymediaMain.response.LoginResponse;
import mymediaMain.response.Response;
import mymediaMain.response.UserResponse;
import mymediaMain.services.AuthService;
import mymediaMain.services.RegistrationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class AuthenticationController {

    private AuthService authService = new AuthService();
    private RegistrationService registrationService = new RegistrationService();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse login(LoginDto loginDto){
        return authService.login(loginDto);
    }

    @GET
    @Path("/logout/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@PathParam("token") String token){
        return authService.logout(token);
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registration(RegistrationDto registrationDto){
        return registrationService.userRegistration(registrationDto);
    }

    @GET
    @Path("/getuserbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse getUserByid(@PathParam("id") String id){
        return authService.getUserById(id);
    }

    @GET
    @Path("/getuserbyusername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse getUserByUsername(@PathParam("username") String username){
        return authService.getUserByUsername(username);
    }

    @GET
    @Path("/getuserbyemail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse getUserByEmail(@PathParam("email") String email){
        return authService.getUserByEmail(email);
    }

    @GET
    @Path("/givemestring")
    @Produces(MediaType.TEXT_PLAIN)
    public String giveMeString(){
        return "Here you are!";
    }

}
