/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.letusgo.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "teawork")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teawork.findAll", query = "SELECT t FROM Teawork t")
    , @NamedQuery(name = "Teawork.findById", query = "SELECT t FROM Teawork t WHERE t.id = :id")
    , @NamedQuery(name = "Teawork.findByTitle", query = "SELECT t FROM Teawork t WHERE t.title = :title")
    , @NamedQuery(name = "Teawork.findByPublicTime", query = "SELECT t FROM Teawork t WHERE t.publicTime = :publicTime")
    , @NamedQuery(name = "Teawork.findByDeadline", query = "SELECT t FROM Teawork t WHERE t.deadline = :deadline")})
public class Teawork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "publicTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicTime;
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teaWorkid")
    private Collection<Stuwork> stuworkCollection;
    @JoinColumn(name = "Termclass_id", referencedColumnName = "id")
    @ManyToOne
    private Termclass termclassid;

    public Teawork() {
    }

    public Teawork(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @XmlTransient
    public Collection<Stuwork> getStuworkCollection() {
        return stuworkCollection;
    }

    public void setStuworkCollection(Collection<Stuwork> stuworkCollection) {
        this.stuworkCollection = stuworkCollection;
    }

    public Termclass getTermclassid() {
        return termclassid;
    }

    public void setTermclassid(Termclass termclassid) {
        this.termclassid = termclassid;
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
        if (!(object instanceof Teawork)) {
            return false;
        }
        Teawork other = (Teawork) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Teawork[ id=" + id + " ]";
    }
    
}
