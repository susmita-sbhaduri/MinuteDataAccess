/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.minutedataaccess.DA;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import org.bhaduri.minutedataaccess.JPA.CalltableJpaController;
import org.bhaduri.minutedataaccess.entities.Calltable;

/**
 *
 * @author sb
 */
public class CallDataAccess extends CalltableJpaController{

    public CallDataAccess(EntityManagerFactory emf) {
        super(emf);
    }
    public List<Calltable> calllistSorted() {
        EntityManager em = getEntityManager();
        TypedQuery<Calltable> query = em.createNamedQuery("Calltable.calllistSorted", Calltable.class);              
        List<Calltable> listofscripdata = query.getResultList();
        return listofscripdata;
    }
    
}
