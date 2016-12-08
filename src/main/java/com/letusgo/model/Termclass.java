/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "termclass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Termclass.findAll", query = "SELECT t FROM Termclass t")
    , @NamedQuery(name = "Termclass.findById", query = "SELECT t FROM Termclass t WHERE t.id = :id")
    , @NamedQuery(name = "Termclass.findByName", query = "SELECT t FROM Termclass t WHERE t.name = :name")})
public class Termclass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "Termteacher_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Termteacher termteacherid;
    @OneToMany(mappedBy = "termclassid")
    private Collection<Selectcourse> selectcourseCollection;
    @OneToMany(mappedBy = "termclassid")
    private Collection<Teawork> teaworkCollection;

    public Termclass() {
    }

    public Termclass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Termteacher getTermteacherid() {
        return termteacherid;
    }

    public void setTermteacherid(Termteacher termteacherid) {
        this.termteacherid = termteacherid;
    }

    @XmlTransient
    public Collection<Selectcourse> getSelectcourseCollection() {
        return selectcourseCollection;
    }

    public void setSelectcourseCollection(Collection<Selectcourse> selectcourseCollection) {
        this.selectcourseCollection = selectcourseCollection;
    }

    @XmlTransient
    public Collection<Teawork> getTeaworkCollection() {
        return teaworkCollection;
    }

    public void setTeaworkCollection(Collection<Teawork> teaworkCollection) {
        this.teaworkCollection = teaworkCollection;
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
        if (!(object instanceof Termclass)) {
            return false;
        }
        Termclass other = (Termclass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Termclass[ id=" + id + " ]";
    }
    
}
