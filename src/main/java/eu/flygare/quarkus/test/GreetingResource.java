package eu.flygare.quarkus.test;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/token")
public class GreetingResource {
    
    // Class just provides blocking and reactive tokens with a 10 second delay so 
    // we can test token caching in good detail
    // Sample project in 

    public final static Logger LOG = Logger.getLogger("Token service");
    
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
    
    AtomicInteger counter = new AtomicInteger(0);
    
    @Path("/gettoken")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking // since we do a long wait 
    public Uni<String> getToken() {
        
        
        LOG.info("getToken called");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            LOG.info("Sleep interrupted");
        }
        
        
        return Uni.createFrom().item("token_" + counter.getAndIncrement());
    }
    
    @Path("/gettokenb")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTokenBlocking() {
        LOG.info("getToken called");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            LOG.info("Sleep interrupted");
        }
        
        
        return "token_" + counter.getAndIncrement();
    }

}