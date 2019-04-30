/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorUsuario;

import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author estef
 */
public class daoUsuario {
    public static boolean ValidarUsuario(String usuario, String contrasenia) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebServicePersistencePU");
        EntityManager entitymanager = emfactory.createEntityManager();
        String query = "SELECT id_usuario, cedula, nombres, apellidos, telefono, direccion, rol FROM usuario WHERE contrasenia='"+contrasenia+"'";
        List<Usuario> resultList =
            entitymanager.createNativeQuery(query, Usuario.class).getResultList();
        boolean bandera = false;
        if (resultList.size()>0) {
            bandera = true;
        }
        return bandera;
    }

    public static Usuario PresentarUsuario(String usuario, String contrasenia) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebServicesRESTPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        String query = "SELECT id_usuario, cedula, nombres, apellidos, telefono, direccion FROM usuario WHERE contrasenia='"+contrasenia+"'";
        Usuario u = new Usuario();
        List<Usuario> resultList =
            entitymanager.createNativeQuery(query, Usuario.class).getResultList();
        if (resultList.size() > 0) {
            u = new Usuario(resultList.get(0).getIdUsuario(),resultList.get(0).getCedula(), resultList.get(0).getNombres(),resultList.get(0).getApellidos(),resultList.get(0).getTelefono(),resultList.get(0).getDireccion(), resultList.get(0).getRol());
        }
        return u;
    }
    
}
