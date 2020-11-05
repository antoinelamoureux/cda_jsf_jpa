/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.cda_jsf_jpa.ejbs;

import com.antoinelamoureux.cda_jsf_jpa.entities.Tags;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TagsFacade extends AbstractFacade<Tags> {
    @PersistenceContext(unitName="my_persistence_unit_jta")
    EntityManager em;
    
    public TagsFacade() {
        super(Tags.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
}
