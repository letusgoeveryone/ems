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
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id")
    , @NamedQuery(name = "Student.findBySn", query = "SELECT s FROM Student s WHERE s.sn = :sn")
    , @NamedQuery(name = "Student.findByName", query = "SELECT s FROM Student s WHERE s.name = :name")
    , @NamedQuery(name = "Student.findByPassword", query = "SELECT s FROM Student s WHERE s.password = :password")
    , @NamedQuery(name = "Student.findBySex", query = "SELECT s FROM Student s WHERE s.sex = :sex")
    , @NamedQuery(name = "Student.findByAvatarid", query = "SELECT s FROM Student s WHERE s.avatarid = :avatarid")
    , @NamedQuery(name = "Student.findByTel", query = "SELECT s FROM Student s WHERE s.tel = :tel")
    , @NamedQuery(name = "Student.findByQq", query = "SELECT s FROM Student s WHERE s.qq = :qq")
    , @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email")
    , @NamedQuery(name = "Student.findByRegdate", query = "SELECT s FROM Student s WHERE s.regdate = :regdate")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "sn",unique = true)
    private String sn;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "sex")
    private Boolean sex;
    @Column(name = "avatarid")
    private String avatarid;
    @Column(name = "tel")
    private String tel;
    @Column(name = "qq")
    private String qq;
    @Column(name = "email")
    private String email;
    @Column(name = "regdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentid")
    private Collection<Stuwork> stuworkCollection;
    @JoinColumn(name = "college_id", referencedColumnName = "id")
    @ManyToOne
    private College college;
    @OneToMany(mappedBy = "studentid")
    private Collection<Selectcourse> selectcourseCollection;

    public Student() {
    }



    public Student( String sn, String name, String password) {

        this.sn = sn;
        this.name = name;
        this.password = password;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAvatarid() {
        return avatarid;
    }

    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    @XmlTransient
    public Collection<Stuwork> getStuworkCollection() {
        return stuworkCollection;
    }

    public void setStuworkCollection(Collection<Stuwork> stuworkCollection) {
        this.stuworkCollection = stuworkCollection;
    }



    @XmlTransient
    public Collection<Selectcourse> getSelectcourseCollection() {
        return selectcourseCollection;
    }

    public void setSelectcourseCollection(Collection<Selectcourse> selectcourseCollection) {
        this.selectcourseCollection = selectcourseCollection;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Student[ id=" + id + " ]";
    }

    /**
     * @return the college
     */
    public College getCollege() {
        return college;
    }

    /**
     * @param college the college to set
     */
    public void setCollege(College college) {
        this.college = college;
    }
    
}
