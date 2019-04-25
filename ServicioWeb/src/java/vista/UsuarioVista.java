/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.google.gson.Gson;
import cotrolador.CntroladorUsuario;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.Usuario;

/**
 * REST Web Service
 *
 * @author Erika
 */
@Path("usuario")
public class UsuarioVista {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioVista
     */
    public UsuarioVista() {
    }

    /**
     * Retrieves representation of an instance of vista.UsuarioVista
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "puto";
    }
    
    @GET
    @Path("get/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsarios() throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        Gson g = new Gson();
        CntroladorUsuario dao = new CntroladorUsuario();
        List<Usuario> usuarios = dao.getUsuario();
        
        return g.toJson(usuarios);
    }
    
    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("insertuser")
    public boolean insertarUsuario(String content) throws ClassNotFoundException, SQLException{
        //TODO return proper representation object
        Gson g = new Gson();
        Usuario u = (Usuario)g.fromJson(content, Usuario.class);
        CntroladorUsuario dao = new CntroladorUsuario();    
        return dao.insertarUsuario(u);
    }
    /**
     * 
     * PUT method for updating or creating an instance of UsuarioVista
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
