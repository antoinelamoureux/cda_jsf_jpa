/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.cda_jsf_jpa.controllers;

import com.antoinelamoureux.cda_jsf_jpa.ejbs.ThemesFacade;
import com.antoinelamoureux.cda_jsf_jpa.entities.Themes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author admin
 */
@Named(value = "themesController")
@SessionScoped
public class ThemesController implements Serializable {
    
    // ON INJECTE LE BEAN DE SESSION
    @EJB
    ThemesFacade ejb;
    
    Themes current;
    
    // DATA MODEL COUPLÉ AVEC UN DATATABLE
    DataModel<Themes> items;                                                                                                      
    
    public ThemesController() {
        System.out.println("ThemesController::Constructor");
        items = null;
    }
    
    public DataModel<Themes> getItems() {
        System.out.println("THIS ==> " + this);
        if (items == null) {
            items = new ListDataModel(getEjb().liste());
        }
        
        return items;
    }
    
    public String prepareCreate() {
        System.out.println("ThemesController::CREATE 1" + this);
        current = new Themes();
        
        return "create";
    }
    
    public String create() {
        getEjb().create(current);
        items = null;
        
        return "liste";
    }
    
    public String prepareUpdate() {
        current  = getItems().getRowData();
        // current = getEjb().find(items.getRowData());   
        
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
        items = null;
        
        return "liste";
    }

    public void setItems(DataModel<Themes> items) {
        this.items = items;
    }

    public ThemesFacade getEjb() {
        return ejb;
    }

    public Themes getCurrent() {
        return current;
    }

    public void setCurrent(Themes current) {
        this.current = current;
    }
    
    public SelectItem[] getSelectItems() {

        List<Themes> liste = getEjb().liste();

        SelectItem[] selectItems = new SelectItem[liste.size() + 1];
        int i = 0;
        selectItems[0] = new SelectItem("", "---");
        i++;

        for (Themes t : liste) {
            
            selectItems[i++] = new SelectItem(t, t.getLibelle());
        }

        return selectItems;

    }
    
}
