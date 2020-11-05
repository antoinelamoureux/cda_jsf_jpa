/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.cda_jsf_jpa.controllers;

import com.antoinelamoureux.cda_jsf_jpa.ejbs.TagsFacade;
import com.antoinelamoureux.cda_jsf_jpa.entities.Tags;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author admin
 */
@Named(value = "tagsController")
@SessionScoped
public class TagsController implements Serializable {
    @EJB
    TagsFacade ejb;  
    Tags current; 
    DataModel<Tags> items;


    public TagsController() {
        System.out.println("TagsController::Controller");
        items = null;
    }
    
    public String prepareCreate() {
        current = new Tags();
        
        return "create";
    }
    
    public String create() {
        getEjb().create(current);
        items = null;
        
        return "liste";
    }
    
    public String prepareUpdate() {
        current= getItems().getRowData();
        
        return "update";
    }
    
    public String update() {
        getEjb().update(current);
        items = null;
        
        return "liste";
    }
    
    public String destroy() {
        current = getItems().getRowData();
        
        try {
            getEjb().destroy(current); 
        } catch(EJBException e) {
            System.out.println("EJBException ==> " + e.getMessage());
        }
        items = null;
        
        return "liste";
    }
    public DataModel<Tags> getItems() {
        if (items == null) {
            items = new ListDataModel(getEjb().liste());
        }
        
        return items;
    }

    public TagsFacade getEjb() {
        return ejb;
    }

    public Tags getCurrent() {
        return current;
    }

    public void setCurrent(Tags current) {
        this.current = current;
    }

    public void setItems(DataModel<Tags> items) {
        this.items = items;
    }
    
    
    
}
