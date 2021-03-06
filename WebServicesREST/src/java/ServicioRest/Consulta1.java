/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicioRest;

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
public class Consulta1 {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of webServices
     */
    public Consulta1() {
    }

    /**
     * Retrieves representation of an instance of servicioRest.webServices
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of webServices
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    @GET
    @Path("listausuario")
    @Produces({MediaType.APPLICATION_JSON})
    public String getData(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException {
        Gson g = new Gson();
        daoUsuario du = new daoUsuario();
        List<Usuario> resultList = du.listEmployees();
        HttpSession session = req.getSession();
        Date date = new Date(session.getLastAccessedTime());
        String log = req.getRemoteAddr() + " - - [" + date + "] "
                + req.getMethod() + " " + req.getRequestURI() + " " + req.getProtocol() + " "
                + res.getStatus() + " " + res.getBufferSize() + "\n";
        Log l = new Log();
        l.escribirLog("C:\\Users\\Erika\\Desktop\\registros\\archivo.log", log);
        return g.toJson(resultList);

    }

}
