/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "termteacher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Termteacher.findAll", query = "SELECT t FROM Termteacher t")
    , @NamedQuery(name = "Termteacher.findById", query = "SELECT t FROM Termteacher t WHERE t.id = :id")
    , @NamedQuery(name = "Termteacher.findByMaxclass", query = "SELECT t FROM Termteacher t WHERE t.maxclass = :maxclass")})
public class Termteacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "maxclass")
    private Integer maxclass;
    @JoinColumn(name = "Teacher_id", referencedColumnName = "id")
    @ManyToOne
    private Teacher teacher;
    @JoinColumn(name = "Termcourse_id", referencedColumnName = "id")
    @ManyToOne
    private Termcourse termcourse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termteacherid")
    private Collection<Termclass> termclassCollection;

    public Termteacher() {
    }

    public Termteacher( Teacher teacher, Termcourse termcourse, Collection<Termclass> termclassCollection) {

        this.teacher = teacher;
        this.termcourse = termcourse;
        this.termclassCollection = termclassCollection;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxclass() {
        return maxclass;
    }

    public void setMaxclass(Integer maxclass) {
        this.maxclass = maxclass;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Termcourse getTermcourse() {
        return termcourse;
    }

    public void setTermcourse(Termcourse termcourseid) {
        this.termcourse = termcourseid;
    }

    @XmlTransient
    public Collection<Termclass> getTermclassCollection() {
        return termclassCollection;
    }

    public void setTermclassCollection(Collection<Termclass> termclassCollection) {
        this.termclassCollection = termclassCollection;
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
        if (!(object instanceof Termteacher)) {
            return false;
        }
        Termteacher other = (Termteacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Termteacher[ id=" + id + " ]";
    }
    
}
