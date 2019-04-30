/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import controladorUsuario.daoUsuario;
import entidades.Usuario;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import sun.misc.BASE64Decoder;

/**
 * REST Web Service
 *
 * @author estef
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of servicios.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("inicio")
    @Produces(MediaType.APPLICATION_JSON)
    public String ingresoUsuario(@HeaderParam("authorization") String authString, @Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException {
        String datos = isUserAuthenticated(authString);
        boolean bandera = false;
        if (datos == null) {
            return "{\"error\\User not authenticated\"}";
        } else {
            daoUsuario du = new daoUsuario();
            String[] parts = datos.split(":");
            Gson g = new Gson();
            bandera = du.ValidarUsuario(parts[0], parts[1]);
            HttpSession session = req.getSession();
            Date date = new Date(session.getLastAccessedTime());
            String log = req.getRemoteAddr() + " - - [" + date + "] "
                    + req.getMethod() + " " + req.getRequestURI() + " " + req.getProtocol() + " "
                    + res.getStatus() + " " + res.getBufferSize() + "\n";
            System.out.println(log);
            if (bandera) {
                Usuario u = du.PresentarUsuario(parts[0], parts[1]);
                return g.toJson(u);
            } else {
                return g.toJson("Usuario Invalido");
            }
        }
    }
    
    private String isUserAuthenticated(String authString) {

        String decodedAuth = "";
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        decodedAuth = new String(bytes);
        System.out.println(decodedAuth);
        return decodedAuth;
    }
}
