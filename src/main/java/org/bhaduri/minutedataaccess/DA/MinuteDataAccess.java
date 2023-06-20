/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.bhaduri.minutedataaccess.DA;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.bhaduri.minutedataaccess.JPA.MinutedataJpaController;
import org.bhaduri.minutedataaccess.entities.Minutedata;
import static org.bhaduri.minutedataaccess.entities.MinutedataPK_.lastupdateminute;

/**
 *
 * @author sb
 */
public class MinuteDataAccess extends MinutedataJpaController{
    
    public MinuteDataAccess(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Minutedata> listByScripid(String scripid) {
        EntityManager em = getEntityManager();
        TypedQuery<Minutedata> query = em.createNamedQuery("Minutedata.listByScripid", Minutedata.class);
        query.setParameter("scripid", scripid);        
        List<Minutedata> listofscripdata = query.getResultList();
        return listofscripdata;
    }
    
    public List<Minutedata> listByScripidLastupdate(String scripid, Date lastupdateDate) {
        EntityManager em = getEntityManager();
        TypedQuery<Minutedata> query = em.createNamedQuery("Minutedata.listByScripidLastupdate", Minutedata.class);
        query.setParameter("scripid", scripid); 
        query.setParameter("lastupdatedate", lastupdateDate);        
        List<Minutedata> listofscripdata = query.getResultList();
        return listofscripdata;
    }
   
}
