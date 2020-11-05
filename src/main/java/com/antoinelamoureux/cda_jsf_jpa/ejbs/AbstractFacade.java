package com.antoinelamoureux.cda_jsf_jpa.ejbs;

// GENERICITÉ ==> LA CLASSE MÈRE À CONNAISSANCE DES ENFANTS QUI VONT L'ÉTENDRE

import java.lang.annotation.Annotation;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import javax.ejb.EJBException;
import javax.persistence.Column;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;


/**
 *
 * @author admin
 */
public abstract class AbstractFacade<T> {
    
    // UN OBJECT DE TYPE CLASS PERMET D'INSTANCIER UN OBJET QUI RÉFÉRENCE UNE CLASSE
    // ENTITYCLASS EST UN OBJECT QUI REPRÉSENTE UNE CLASSE
    
    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entityClass) {
        // ON AGREGE L'OBJECT REPRÉSENTANT UNE CLASSE
        this.entityClass = entityClass;
    }
    
    // MÉTHODE ABSTRAITE POUR RÉCUPÉRER L'ENTITY MANAGER (GÉRÉE PAR LES CLASSES FILLES)
    abstract public EntityManager getEntityManager();
    

    public List<T> liste() {
        //Query query = getEntityManager().createNamedQuery("Themes.findAll");
        //Query query = getEntityManager().createQuery("SELECT t FROM Themes t");
        
        // AVEC UN CRITERIA QUERY
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq = cq.select(cq.from(entityClass));
        
        //Query query = getEntityManager().createQuery(cq);
        
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public T findById(int id) {
        Annotation[] annotations = entityClass.getDeclaredAnnotations();
        String columnName = "";
        
        for(Annotation annotation : annotations){
            if(annotation instanceof Column){
        Column column = (Column) annotation;
        columnName = column.name();
            }
        }
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq;
        cq = cb.createQuery(entityClass);
        Root<T> rootEntity = cq.from(entityClass);
        cq = cq.select(cq.from(entityClass)).where(cb.equal(rootEntity.get(columnName), id));
    
        
        return (T) getEntityManager().createQuery(cq).getSingleResult();
    }
    
     public void create(T entityClass) {
         getEntityManager().persist(entityClass);
    }
     

    public T find(int id) {
        return getEntityManager().find(entityClass, id);
    }

     
     public void update(T entityClass) {
         getEntityManager().merge(entityClass);
     }
     
     public void destroy(T entityClass) throws EJBException {
            getEntityManager().remove(getEntityManager().merge(entityClass)); 
     }
     
}
