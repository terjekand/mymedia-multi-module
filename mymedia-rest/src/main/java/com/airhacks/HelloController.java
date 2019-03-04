package com.airhacks;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/welcome")
public class HelloController {
    @GET
    @Path("sayhello")
    public String sayHello(){
        return "Hello!";
    }
}
