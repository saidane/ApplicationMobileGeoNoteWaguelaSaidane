/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nabil
 */
@Entity
@Table(name = "parcours")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parcours.findAll", query = "SELECT p FROM Parcours p"),
    @NamedQuery(name = "Parcours.findByIdParcours", query = "SELECT p FROM Parcours p WHERE p.idParcours = :idParcours"),
    @NamedQuery(name = "Parcours.findByTitre", query = "SELECT p FROM Parcours p WHERE p.titre = :titre"),
    @NamedQuery(name = "Parcours.findByCommentaire", query = "SELECT p FROM Parcours p WHERE p.commentaire = :commentaire")})
public class Parcours implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parcours")
    private Integer idParcours;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "commentaire")
    private String commentaire;
    @JoinTable(name = "parcours_has_note", joinColumns = {
        @JoinColumn(name = "parcours_id_parcours", referencedColumnName = "id_parcours")}, inverseJoinColumns = {
        @JoinColumn(name = "note_id_note", referencedColumnName = "id_note")})
    @ManyToMany
    private Collection<Note> noteCollection;

    public Parcours() {
    }

    public Parcours(Integer idParcours) {
        this.idParcours = idParcours;
    }

    public Parcours(Integer idParcours, String titre, String commentaire) {
        this.idParcours = idParcours;
        this.titre = titre;
        this.commentaire = commentaire;
    }

    public Integer getIdParcours() {
        return idParcours;
    }

    public void setIdParcours(Integer idParcours) {
        this.idParcours = idParcours;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @XmlTransient
    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParcours != null ? idParcours.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcours)) {
            return false;
        }
        Parcours other = (Parcours) object;
        if ((this.idParcours == null && other.idParcours != null) || (this.idParcours != null && !this.idParcours.equals(other.idParcours))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Parcours[ idParcours=" + idParcours + " ]";
    }
    
}
