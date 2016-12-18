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
import javax.persistence.FetchType;
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
@Table(name = "termcourse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Termcourse.findAll", query = "SELECT t FROM Termcourse t")
    , @NamedQuery(name = "Termcourse.findById", query = "SELECT t FROM Termcourse t WHERE t.id = :id")
    , @NamedQuery(name = "Termcourse.findByTerm", query = "SELECT t FROM Termcourse t WHERE t.term = :term")})
public class Termcourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "term")
    private String term;
    @JoinColumn(name = "Course_id", referencedColumnName = "id")
    @ManyToOne
    private Course course;
    @JoinColumn(name = "Teacher_id", referencedColumnName = "id")
    @ManyToOne
    private Teacher teacher;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termcourse")
    private Collection<Termteacher> termteacherCollection;

    public Termcourse() {
    }

    public Termcourse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @XmlTransient
    public Collection<Termteacher> getTermteacherCollection() {
        return termteacherCollection;
    }

    public void setTermteacherCollection(Collection<Termteacher> termteacherCollection) {
        this.termteacherCollection = termteacherCollection;
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
        if (!(object instanceof Termcourse)) {
            return false;
        }
        Termcourse other = (Termcourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Termcourse[ id=" + id + " ]";
    }
    
}
