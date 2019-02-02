package auth;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@ApplicationPath("/resources")
@Path("/example")
public class MyMediaRestExample extends Application {


    @GET
    @Path(("/sayhello"))
    @Consumes(MediaType.APPLICATION_JSON)
    public String sayHello(){
        return "hello";
    }
}
