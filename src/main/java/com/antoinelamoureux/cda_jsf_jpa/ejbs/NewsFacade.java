/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.cda_jsf_jpa.ejbs;

import com.antoinelamoureux.cda_jsf_jpa.entities.News;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class NewsFacade extends AbstractFacade<News> {
    @PersistenceContext(unitName="my_persistence_unit_jta")
    EntityManager em;

    public NewsFacade() {
        super(News.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }  
  
}
