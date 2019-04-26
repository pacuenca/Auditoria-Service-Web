/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author estef
 */
public class main {
    public static void main(String[] args) {
       EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebServicesRESTPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        Usuario employee = entitymanager.find(Usuario.class, 1);

        System.out.println("employee ID = " + employee.getApellidos()); 
    }
}
