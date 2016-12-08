/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "logininfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logininfo.findAll", query = "SELECT l FROM Logininfo l")
    , @NamedQuery(name = "Logininfo.findById", query = "SELECT l FROM Logininfo l WHERE l.id = :id")
    , @NamedQuery(name = "Logininfo.findBySn", query = "SELECT l FROM Logininfo l WHERE l.sn = :sn")
    , @NamedQuery(name = "Logininfo.findByLastLoginDate", query = "SELECT l FROM Logininfo l WHERE l.lastLoginDate = :lastLoginDate")
    , @NamedQuery(name = "Logininfo.findByLastLoginIp", query = "SELECT l FROM Logininfo l WHERE l.lastLoginIp = :lastLoginIp")})
public class Logininfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sn")
    private String sn;
    @Column(name = "lastLoginDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;
    @Column(name = "lastLoginIp")
    private String lastLoginIp;

    public Logininfo() {
    }

    public Logininfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logininfo)) {
            return false;
        }
        Logininfo other = (Logininfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Logininfo[ id=" + id + " ]";
    }
    
}
