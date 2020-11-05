/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.cda_jsf_jpa.controllers;

import com.antoinelamoureux.cda_jsf_jpa.entities.Themes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass = Themes.class)
public class ThemeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

            if (value == null || value.length() == 0) {
                return null;
            }

            ThemesController controller = (ThemesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "themesController");
            
            //TypedQuery<Themes> query = controller.getEjb().getEntityManager().createNamedQuery("Themes.findByIdNews", Themes.class);

            return controller.getEjb().find(getKey(value));
    }

    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        System.out.println("THEME (as string) : " + t);
        if (t == null) {
            return null;
        }
        
        if (t instanceof Themes) {
            Themes object = (Themes) t;
            return getStringKey(object.getIdTheme());
        } else {
           throw new IllegalArgumentException("object " + t + " is of type " + t.getClass().getName() + "; expected type: " + Themes.class.getName()); 
        }
    }
    
    public String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        
        return sb.toString();
    }
   
    public java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        
        return key;
    }

    
}
