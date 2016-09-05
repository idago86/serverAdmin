/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveradmin.model;

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
 * @author Mihaela Tudose
 */
@Entity
@Table(name = "admini")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminDB.findAll", query = "SELECT a FROM AdminDB a"),
    @NamedQuery(name = "AdminDB.findById", query = "SELECT a FROM AdminDB a WHERE a.id = :id"),
    @NamedQuery(name = "AdminDB.findByUser", query = "SELECT a FROM AdminDB a WHERE a.user = :user"),
    @NamedQuery(name = "AdminDB.findByParola", query = "SELECT a FROM AdminDB a WHERE a.parola = :parola")})
public class AdminDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user")
    private String user;
    @Basic(optional = false)
    @Column(name = "parola")
    private String parola;

    public AdminDB() {
    }

    public AdminDB(Integer id) {
        this.id = id;
    }

    public AdminDB(Integer id, String user, String parola) {
        this.id = id;
        this.user = user;
        this.parola = parola;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
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
        if (!(object instanceof AdminDB)) {
            return false;
        }
        AdminDB other = (AdminDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "serveradmin.model.AdminDB[ id=" + id + " ]";
    }
    
}
