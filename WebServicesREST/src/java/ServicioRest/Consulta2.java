/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioRest;

import com.google.gson.Gson;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import controladorUsuario.daoUsuario;
import modelos.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import log.Log;
import sun.misc.BASE64Decoder;

/**
 * REST Web Service ++
 *
 * @author Erika
 */
@Path("generic")
public class webServices {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of webServices
     */
    public webServices() {
    }


    @GET
    @Path("inicio")
    @Produces(MediaType.APPLICATION_JSON)
    public String ingresoUsuario(@HeaderParam("authorization") String authString, @Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException {
        String datos = isUserAuthenticated(authString);
        boolean bandera = false;
        if (datos == null) {
            return "{\"error\":\"User not authenticated\"}";
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
            Log l = new Log();
            l.escribirLog("C:\\Users\\Erika\\Desktop\\registros\\archivo.log", log);
            if (bandera) {
                Usuario u = du.PresentarUsuario(parts[0], parts[1]);
                return g.toJson(u);
            } else {
                return g.toJson("Usuario Invalido");
            }
        }
    }
