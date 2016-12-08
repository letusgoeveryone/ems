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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "stuwork")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stuwork.findAll", query = "SELECT s FROM Stuwork s")
    , @NamedQuery(name = "Stuwork.findById", query = "SELECT s FROM Stuwork s WHERE s.id = :id")
    , @NamedQuery(name = "Stuwork.findByScore", query = "SELECT s FROM Stuwork s WHERE s.score = :score")
    , @NamedQuery(name = "Stuwork.findByComment", query = "SELECT s FROM Stuwork s WHERE s.comment = :comment")
    , @NamedQuery(name = "Stuwork.findByState", query = "SELECT s FROM Stuwork s WHERE s.state = :state")
    , @NamedQuery(name = "Stuwork.findByCommitTime", query = "SELECT s FROM Stuwork s WHERE s.commitTime = :commitTime")
    , @NamedQuery(name = "Stuwork.findByCorrectTime", query = "SELECT s FROM Stuwork s WHERE s.correctTime = :correctTime")})
public class Stuwork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "score")
    private Integer score;
    @Column(name = "comment")
    private String comment;
    @Column(name = "state")
    private Integer state;
    @Column(name = "commitTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commitTime;
    @Column(name = "correctTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date correctTime;
    @JoinColumn(name = "TeaWork_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Teawork teaWorkid;
    @JoinColumn(name = "Student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentid;

    public Stuwork() {
    }

    public Stuwork(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public Date getCorrectTime() {
        return correctTime;
    }

    public void setCorrectTime(Date correctTime) {
        this.correctTime = correctTime;
    }

    public Teawork getTeaWorkid() {
        return teaWorkid;
    }

    public void setTeaWorkid(Teawork teaWorkid) {
        this.teaWorkid = teaWorkid;
    }

    public Student getStudentid() {
        return studentid;
    }

    public void setStudentid(Student studentid) {
        this.studentid = studentid;
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
        if (!(object instanceof Stuwork)) {
            return false;
        }
        Stuwork other = (Stuwork) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Stuwork[ id=" + id + " ]";
    }
    
}
