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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *选课信息.<br>
 * 记录选课信息
 * @author 王鸿运
 * @version 1.0
 */
@Entity
@Table(name = "selectcourse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Selectcourse.findAll", query = "SELECT s FROM Selectcourse s")
    , @NamedQuery(name = "Selectcourse.findById", query = "SELECT s FROM Selectcourse s WHERE s.id = :id")
    , @NamedQuery(name = "Selectcourse.findByState", query = "SELECT s FROM Selectcourse s WHERE s.state = :state")})
public class Selectcourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "state")
    private Integer state;
    @JoinColumn(name = "Student_id", referencedColumnName = "id")
    @ManyToOne
    private Student studentid;
    @JoinColumn(name = "Termclass_id", referencedColumnName = "id")
    @ManyToOne
    private Termclass termclassid;

    public Selectcourse() {
    }

    public Selectcourse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Student getStudentid() {
        return studentid;
    }

    public void setStudentid(Student studentid) {
        this.studentid = studentid;
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
        if (!(object instanceof Selectcourse)) {
            return false;
        }
        Selectcourse other = (Selectcourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Selectcourse[ id=" + id + " ]";
    }
    
}
