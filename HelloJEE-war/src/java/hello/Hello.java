package hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Hello {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.status(201).entity("{ \"message\" : \"Hello\" }").build();
    }

    @GET
    @Path("/fib/i/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fib_i(@PathParam("n") String n) {
        Fibonacci fib = new Fibonacci();

        long millis = System.nanoTime();
        String f = fib.iterative(n);
        millis = System.nanoTime() - millis;
        
        String result = "{ \"result\": \"" + f
                + "\", \"calculation time\" : " + (millis / 1000000f) + " }";

        return Response.status(201).entity(result).build();
    }

    @GET
    @Path("/fib/a/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fib_a(@PathParam("n") String n) {
        Fibonacci fib = new Fibonacci();

        long millis = System.nanoTime();
        String f = fib.analytic(n);
        millis = System.nanoTime() - millis;
        
        String result = "{ \"result\": \"" + f
                + "\", \"calculation time\" : " + (millis / 1000000f) + " }";

        return Response.status(201).entity(result).build();
    }
}
