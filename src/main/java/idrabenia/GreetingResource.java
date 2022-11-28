package idrabenia;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/hello")
public class GreetingResource {

    @GET()
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@PathParam("name") String name) {
        return Response.ok()
                .entity(Map.of("message", "Hello " + name))
                .build();
    }
}
