package auth;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@ApplicationPath("/resources")
@Path("/example")
public class MyMediaRestExample extends Application {


    @GET
    @Path(("/sayhello"))
    //@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String sayHello(){
        return "hello";
    }
}
