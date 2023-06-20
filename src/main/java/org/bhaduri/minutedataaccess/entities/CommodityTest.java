/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.minutedataaccess.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "commodity_test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityTest.findAll", query = "SELECT c FROM CommodityTest c")})
public class CommodityTest implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityTestPK commodityTestPK;
    @Basic(optional = false)
    @Column(name = "daylastprice")
    private double daylastprice;
    @Basic(optional = false)
    @Column(name = "dayhighprice")
    private double dayhighprice;
    @Basic(optional = false)
    @Column(name = "daylowprice")
    private double daylowprice;
    @Basic(optional = false)
    @Column(name = "prevcloseprice")
    private double prevcloseprice;

    public CommodityTest() {
    }

    public CommodityTest(CommodityTestPK commodityTestPK) {
        this.commodityTestPK = commodityTestPK;
    }

    public CommodityTest(CommodityTestPK commodityTestPK, double daylastprice, double dayhighprice, double daylowprice, double prevcloseprice) {
        this.commodityTestPK = commodityTestPK;
        this.daylastprice = daylastprice;
        this.dayhighprice = dayhighprice;
        this.daylowprice = daylowprice;
        this.prevcloseprice = prevcloseprice;
    }

    public CommodityTest(String scripid, Date lastupdateminute) {
        this.commodityTestPK = new CommodityTestPK(scripid, lastupdateminute);
    }

    public CommodityTestPK getCommodityTestPK() {
        return commodityTestPK;
    }

    public void setCommodityTestPK(CommodityTestPK commodityTestPK) {
        this.commodityTestPK = commodityTestPK;
    }

    public double getDaylastprice() {
        return daylastprice;
    }

    public void setDaylastprice(double daylastprice) {
        this.daylastprice = daylastprice;
    }

    public double getDayhighprice() {
        return dayhighprice;
    }

    public void setDayhighprice(double dayhighprice) {
        this.dayhighprice = dayhighprice;
    }

    public double getDaylowprice() {
        return daylowprice;
    }

    public void setDaylowprice(double daylowprice) {
        this.daylowprice = daylowprice;
    }

    public double getPrevcloseprice() {
        return prevcloseprice;
    }

    public void setPrevcloseprice(double prevcloseprice) {
        this.prevcloseprice = prevcloseprice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityTestPK != null ? commodityTestPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityTest)) {
            return false;
        }
        CommodityTest other = (CommodityTest) object;
        if ((this.commodityTestPK == null && other.commodityTestPK != null) || (this.commodityTestPK != null && !this.commodityTestPK.equals(other.commodityTestPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.minutedataaccess.entities.CommodityTest[ commodityTestPK=" + commodityTestPK + " ]";
    }
    
}
