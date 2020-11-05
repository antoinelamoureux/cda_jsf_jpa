/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.cda_jsf_jpa.ejbs;

import com.antoinelamoureux.cda_jsf_jpa.entities.Themes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ThemesFacade extends AbstractFacade<Themes> {
    // INJECTION D'UN ENTITY MANAGER
    @PersistenceContext(unitName="my_persistence_unit_jta")
    EntityManager em;
    
    public ThemesFacade() {
        super(Themes.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
}
