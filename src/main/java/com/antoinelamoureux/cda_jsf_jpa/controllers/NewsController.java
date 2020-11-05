/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.cda_jsf_jpa.controllers;

import com.antoinelamoureux.cda_jsf_jpa.ejbs.NewsFacade;
import com.antoinelamoureux.cda_jsf_jpa.entities.News;
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
@Named(value = "newsController")
@SessionScoped
public class NewsController implements Serializable {
    
    @EJB
    NewsFacade ejb;
    
    News current;
    
    DataModel<News> items;

    public NewsController() {
        System.out.println("NewsController::Constructor");
        items = null;
    }
    
    public String prepareCreate() {
        System.out.println("NewsController::CREATE 1" + this);
        current = new News();
        
        return "create";
    }
    
    public String create() {
        //current.setTheme(new Theme(value));
        getEjb().create(current);
        //System.out.println("CURRENT NEWS :" + current.toString());
        items = null;
        
        return "liste";
    }
    
    public String prepareUpdate() {
        System.out.println("NewsController::CREATE 1" + this);
        current = getItems().getRowData();
        
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
        } catch (EJBException e) {
            System.out.println("EJBException ==> " + e.getMessage());
        }
        
        return "liste";
    }

    public NewsFacade getEjb() {
        return ejb;
    }

    public News getCurrent() {
        return current;
    }

    public void setCurrent(News current) {
        this.current = current;
    }

    public DataModel<News> getItems() {
        if (items == null) {
            items = new ListDataModel(getEjb().liste());
        }
        
        return items;
    }

    public void setItems(DataModel<News> items) {
        this.items = items;
    }
    
    
}
