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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "teacher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t")
    , @NamedQuery(name = "Teacher.findById", query = "SELECT t FROM Teacher t WHERE t.id = :id")
    , @NamedQuery(name = "Teacher.findBySn", query = "SELECT t FROM Teacher t WHERE t.sn = :sn")
    , @NamedQuery(name = "Teacher.findByName", query = "SELECT t FROM Teacher t WHERE t.name = :name")
    , @NamedQuery(name = "Teacher.findByPassword", query = "SELECT t FROM Teacher t WHERE t.password = :password")
    , @NamedQuery(name = "Teacher.findBySex", query = "SELECT t FROM Teacher t WHERE t.sex = :sex")
    , @NamedQuery(name = "Teacher.findByAvatarid", query = "SELECT t FROM Teacher t WHERE t.avatarid = :avatarid")
    , @NamedQuery(name = "Teacher.findByTel", query = "SELECT t FROM Teacher t WHERE t.tel = :tel")
    , @NamedQuery(name = "Teacher.findByQq", query = "SELECT t FROM Teacher t WHERE t.qq = :qq")
    , @NamedQuery(name = "Teacher.findByEmail", query = "SELECT t FROM Teacher t WHERE t.email = :email")
    , @NamedQuery(name = "Teacher.findByRegdate", query = "SELECT t FROM Teacher t WHERE t.regdate = :regdate")
    , @NamedQuery(name = "Teacher.findByRoleid", query = "SELECT t FROM Teacher t WHERE t.roleid = :roleid")})
public class Teacher implements Serializable {

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
    @Column(name = "roleid")
    private String roleid;
    @OneToMany(mappedBy = "teacher",fetch=FetchType.EAGER)
    private Collection<Termcourse> termcourseCollection;
    @JoinColumn(name = "college_id", referencedColumnName = "id")
    @ManyToOne
    private College college;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher",fetch=FetchType.EAGER)
    private Collection<Termteacher> termteacherCollection;

    public Teacher() {
    }



    public Teacher( String sn, String name, String password) {
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

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    @XmlTransient
    public Collection<Termcourse> getTermcourseCollection() {
        return termcourseCollection;
    }

    public void setTermcourseCollection(Collection<Termcourse> termcourseCollection) {
        this.termcourseCollection = termcourseCollection;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
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
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
	public String toString() {
		return "Teacher [id=" + id + ", sn=" + sn + ", name=" + name + ", password=" + password + ", sex=" + sex
				+ ", avatarid=" + avatarid + ", tel=" + tel + ", qq=" + qq + ", email=" + email + ", regdate=" + regdate
				+ ", roleid=" + roleid + ", termcourseCollection=" + termcourseCollection + ", college=" + college
				+ ", termteacherCollection=" + termteacherCollection + "]";
	}
    
}
