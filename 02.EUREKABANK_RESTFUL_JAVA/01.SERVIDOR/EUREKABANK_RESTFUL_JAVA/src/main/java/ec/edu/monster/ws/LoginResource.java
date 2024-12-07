
package ec.edu.monster.ws;

import ec.edu.monster.service.LoginService;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author danie
 */
@Path("login")
@RequestScoped
public class LoginResource {

    public static class LoginRequest {
        private String username;
        private String password;

        // Default no-argument constructor
        public LoginRequest() {
        }

        // Getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * Authenticates a user.
     * @param request The login request object.
     * @return A response indicating whether authentication was successful.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean authenticate(LoginRequest request) {
        LoginService service = new LoginService();
        return service.login(request.getUsername(), request.getPassword());
    }
}


