/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.minutedataaccess.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "scrips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scrips.findAll", query = "SELECT s FROM Scrips s")})
public class Scrips implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "scripid")
    private String scripid;

    public Scrips() {
    }

    public Scrips(String scripid) {
        this.scripid = scripid;
    }

    public String getScripid() {
        return scripid;
    }

    public void setScripid(String scripid) {
        this.scripid = scripid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scripid != null ? scripid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scrips)) {
            return false;
        }
        Scrips other = (Scrips) object;
        if ((this.scripid == null && other.scripid != null) || (this.scripid != null && !this.scripid.equals(other.scripid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.minutedataaccess.entities.Scrips[ scripid=" + scripid + " ]";
    }
    
}
