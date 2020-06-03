/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicslab;

import ConexionBaseDatos.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

/**
 *
 * @author Jowen
 */
public class PhysicsLab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            recuperarUsuario(ConexionBaseDatos.obtener(), "adiaz");
        } catch (SQLException ex) {
            Logger.getLogger(PhysicsLab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhysicsLab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void recuperarUsuario(Connection conexion, String user) throws SQLException {

        try {
            Usuario usuario = new Usuario();
            PreparedStatement consulta = conexion.prepareStatement("SELECT user, user_name, user_lastname, password FROM user WHERE user = ?");
            consulta.setString(1, user);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                System.out.println(resultado.getString("user"));
                System.out.println(resultado.getString("user_name"));
                System.out.println(resultado.getString("user_lastname"));
                System.out.println(resultado.getString("password"));

                // usuario.setNombre(resultado.getString("user_name"));
                // tarea = new Tarea(id_tarea, resultado.getString("titulo"), resultado.getString("descripcion"), resultado.getInt("nivel_de_prioridad"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        // return null;
    }

    public String registrarUsuario(Connection conexion, Usuario user) throws SQLException {
        String mensaje = null;
        try {

            PreparedStatement consulta = conexion.prepareStatement("INSERT INTO user (user_name,user_lastname,user,password) values(?,?,?,?)");
            consulta.setString(1, user.getNombre());
            consulta.setString(2, user.getApellido());
            consulta.setString(3, user.getUsuario());
            consulta.setString(4, user.getPassword());
            boolean resultado = consulta.execute();

            if (resultado) {
                mensaje = "usuario regisgtrado exitosamente";
            } else {
                mensaje = "no se pudo registar el usuario";
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        // return null;
        return mensaje;
    }

    }
    
}
