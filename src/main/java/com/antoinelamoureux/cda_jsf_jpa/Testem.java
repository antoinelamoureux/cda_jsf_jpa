/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.cda_jsf_jpa;

import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author admin
 */
@WebServlet(name = "Testem", urlPatterns = {"/app"})
public class Testem extends HttpServlet {
    
    // INJECTION D'UN ENTITY MANAGER - JTA (Datasource)
    @PersistenceContext(unitName = "my_persistence_unit_jta")
    EntityManager emWithDs;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Instancier un em avec une unité de persistance depuis une datasource
        // JNDI lookup
        
        System.out.println("EntityManagher INJECTED ==> " + emWithDs);
        
        DataSource ds;
        try {
            Context context= new InitialContext();
            ds = (DataSource) context.lookup("MyDataSource");
            System.out.println("DATASOURCE ==> " + ds);
        } catch(NamingException e) {
            
        }
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        // Instanciation d'un EntityManager
        EntityManager em = emf.createEntityManager();
        
        System.out.println("emf ==> " + emf);
        System.out.println("em ==> " + em);

        try{

            // On démarre nous-même les transactions
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            
            System.out.println("transaction ==> " + transaction);
            
            // em faire des requètes

            // em dealer avec le dépôt d'objets
            // persist() - merge()

            // transaction 'pousser' en base de données
            // commit() - rollBack()




        }
        catch (Exception ex){
            System.err.println(ex);
        }
        finally {
            if (em != null){
                em.close();
            }
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
