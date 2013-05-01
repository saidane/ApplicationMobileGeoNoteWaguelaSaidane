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
@Table(name = "note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findByIdNote", query = "SELECT n FROM Note n WHERE n.idNote = :idNote"),
    @NamedQuery(name = "Note.findByAdresse", query = "SELECT n FROM Note n WHERE n.adresse = :adresse"),
    @NamedQuery(name = "Note.findByTitre", query = "SELECT n FROM Note n WHERE n.titre = :titre"),
    @NamedQuery(name = "Note.findByDescription", query = "SELECT n FROM Note n WHERE n.description = :description")})
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_note")
    private Integer idNote;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "description")
    private String description;
    @ManyToMany(mappedBy = "noteCollection")
    private Collection<Parcours> parcoursCollection;

    public Note() {
    }

    public Note(Integer idNote) {
        this.idNote = idNote;
    }

    public Note(Integer idNote, String adresse, String titre, String description) {
        this.idNote = idNote;
        this.adresse = adresse;
        this.titre = titre;
        this.description = description;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Parcours> getParcoursCollection() {
        return parcoursCollection;
    }

    public void setParcoursCollection(Collection<Parcours> parcoursCollection) {
        this.parcoursCollection = parcoursCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNote != null ? idNote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.idNote == null && other.idNote != null) || (this.idNote != null && !this.idNote.equals(other.idNote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Note[ idNote=" + idNote + " ]";
    }
    
}
