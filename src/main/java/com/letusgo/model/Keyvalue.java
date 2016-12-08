/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "keyvalue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Keyvalue.findAll", query = "SELECT k FROM Keyvalue k")
    , @NamedQuery(name = "Keyvalue.findById", query = "SELECT k FROM Keyvalue k WHERE k.id = :id")
    , @NamedQuery(name = "Keyvalue.findByIkey", query = "SELECT k FROM Keyvalue k WHERE k.ikey = :ikey")
    , @NamedQuery(name = "Keyvalue.findByIvalue", query = "SELECT k FROM Keyvalue k WHERE k.ivalue = :ivalue")})
public class Keyvalue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ikey")
    private String ikey;
    @Column(name = "ivalue")
    private String ivalue;

    public Keyvalue() {
    }



    public Keyvalue( String ikey) {
        this.ikey = ikey;
    }

    public Keyvalue( String ikey, String ivalue) {
        this.ikey = ikey;
        this.ivalue = ivalue;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIkey() {
        return ikey;
    }

    public void setIkey(String ikey) {
        this.ikey = ikey;
    }

    public String getIvalue() {
        return ivalue;
    }

    public void setIvalue(String ivalue) {
        this.ivalue = ivalue;
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
        if (!(object instanceof Keyvalue)) {
            return false;
        }
        Keyvalue other = (Keyvalue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Keyvalue[ id=" + id + " ]";
    }
    
}
