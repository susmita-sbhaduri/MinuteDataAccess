/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.minutedataaccess.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "calltable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calltable.findAll", query = "SELECT c FROM Calltable c")})
public class Calltable implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CalltablePK calltablePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "callone")
    private String callone;
    @Column(name = "calltwo")
    private String calltwo;
    @Column(name = "retraceone")
    private Double retraceone;
    @Column(name = "retracetwo")
    private Double retracetwo;

    public Calltable() {
    }

    public Calltable(CalltablePK calltablePK) {
        this.calltablePK = calltablePK;
    }

    public Calltable(String scripid, Date lastupdateminute) {
        this.calltablePK = new CalltablePK(scripid, lastupdateminute);
    }

    public CalltablePK getCalltablePK() {
        return calltablePK;
    }

    public void setCalltablePK(CalltablePK calltablePK) {
        this.calltablePK = calltablePK;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCallone() {
        return callone;
    }

    public void setCallone(String callone) {
        this.callone = callone;
    }

    public String getCalltwo() {
        return calltwo;
    }

    public void setCalltwo(String calltwo) {
        this.calltwo = calltwo;
    }

    public Double getRetraceone() {
        return retraceone;
    }

    public void setRetraceone(Double retraceone) {
        this.retraceone = retraceone;
    }

    public Double getRetracetwo() {
        return retracetwo;
    }

    public void setRetracetwo(Double retracetwo) {
        this.retracetwo = retracetwo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calltablePK != null ? calltablePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calltable)) {
            return false;
        }
        Calltable other = (Calltable) object;
        if ((this.calltablePK == null && other.calltablePK != null) || (this.calltablePK != null && !this.calltablePK.equals(other.calltablePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.minutedataaccess.entities.Calltable[ calltablePK=" + calltablePK + " ]";
    }
    
}
