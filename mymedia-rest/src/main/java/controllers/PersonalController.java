package controllers;

import mymediaMain.dto.UpdateProfileDto;
import mymediaMain.response.PersonalDataResponse;
import mymediaMain.response.Response;
import mymediaMain.services.PersonalService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/personal")
public class PersonalController {

    PersonalService personalService = new PersonalService();

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePersonalData(UpdateProfileDto updateProfileDto){
        return personalService.updatePersonalData(updateProfileDto);
    }

    @GET
    @Path("/getdata/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonalDataResponse updatePersonalData(@PathParam("id") String userId){
        return personalService.getPersonalData(userId);
    }


}
