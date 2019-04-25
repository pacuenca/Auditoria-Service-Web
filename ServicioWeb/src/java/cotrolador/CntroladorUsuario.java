/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotrolador;

import base.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author Erika
 */
public class CntroladorUsuario {

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public List<Usuario> getUsuario() throws ClassNotFoundException, SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario u = null;
        sql = "SELECT * FROM usuario";
        con = conexion.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            u = new Usuario();
            u.setId(rs.getInt("idusuario"));
            u.setNombre(rs.getString("nombre"));
            u.setContrasenia(rs.getString("contrasenia"));
            usuarios.add(u);
        }
        conexion.db();
        return usuarios;
    }

    public boolean insertarUsuario(Usuario u) {
        try {
            sql = "INSERT INTO usuario(idusuario,nombre,contrasenia) VALUES (?,?,?);";
            con = conexion.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, u.getId());
            pst.setString(2, u.getNombre());
            pst.setString(3, u.getContrasenia());
            pst.execute();
            conexion.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CntroladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ingresoSistema(String usuario, String contrasenia) {
        boolean validar = false;
        try {
            sql = "SELECT * FROM usuario WHERE login = '" + usuario + "' and contrasenia = '" + contrasenia + "';";
            con = conexion.cb();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                if(rs.getString("contrasenia").equals(contrasenia)){
                    validar = true;
                }
            }
            return validar;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("error aqui");
            Logger.getLogger(CntroladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
