/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entity.Note;
import entity.Parcours;
import entity.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.NoteFacade;
import session.ParcoursFacade;
import session.UtilisateurFacade;

/**
 *
 * @author Hela Saidane - Axel Brice WAGUELA
 */
@WebService(serviceName = "GeonoteWS")
public class GeonoteWS {

    @EJB
    private NoteFacade noteFacade;
    @EJB
    private UtilisateurFacade utilisateurFacade;
    @EJB
    private ParcoursFacade parcoursFacade;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "EnregistrerNote")
    public String EnregistrerNote(@WebParam(name = "adresse") String adresse, @WebParam(name = "titre") String titre, @WebParam(name = "description") String description) {
        //TODO write your implementation code here:
        List<Note> list = noteFacade.findAll();
        Note note = new Note();
        note.setIdNote(0);
        note.setAdresse(adresse);
        note.setTitre(titre);
        note.setDescription(description);
        noteFacade.create(note);

        return "Note de " + titre + " est bien enregistrée";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "showNotes")
    public String showNotes() {
        List<Note> list_note = noteFacade.findAll();
        int n = 1;
        Note note = new Note();
        String ss = "<form><select  style='width:auto; background:#FFF0F5; border-style:solid; border-color:#98bf21' size='7'  name='multinote' multiple='multiple'>";
        for (int i = 0; i < list_note.size(); i++) {
            note = list_note.get(i);
            ss = ss + "<option  value='" + n + "'>" + n + "eme note: " + note.getTitre() + " @ " + note.getAdresse() + "</option>";
            n++;
        }
        ss = ss + "</select></form>";
        return ss;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ajouterNoteParcours")
    public String ajouterNoteParcours(@WebParam(name = "tit") String tit, @WebParam(name = "com") String com, @WebParam(name = "idNotes") String idNotes) {

        List<Note> list_note = noteFacade.findAll();
        List<Note> list_note_parcours = new ArrayList<Note>();
        List<Parcours> list_parcours = parcoursFacade.findAll();
        int n = 1;
        Note note = new Note();
        String[] ids = idNotes.split(",");
        for (int i = 0; i < list_note.size(); i++) {
            for (int j = 0; j < ids.length; j++) {
                if (!ids[j].isEmpty()) {
                    if (list_note.get(i).getIdNote() == Integer.parseInt(ids[j])) {
                        note = list_note.get(i);
                        list_note_parcours.add(note);
                        break;
                    }
                }
            }
        }
        Parcours parcours = new Parcours();
        parcours.setIdParcours(0);
        parcours.setTitre(tit);
        parcours.setCommentaire(com);
        parcours.setNoteCollection(list_note_parcours);
        parcoursFacade.create(parcours);
        return "Vous avez bien enregistré le nouvel parcours : " + tit + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "showParcours")
    public String showParcours() {
        List<Parcours> list_parcours = parcoursFacade.findAll();
        int n = 1;
        List<Note> test;
        Parcours parcours = new Parcours();
       
        String ss = "<form><select style='width:auto; background:#FFF0F5; border-style:solid; border-color:#98bf21' size='5'  name='multiparcours' >";
        for (int i = 0; i < list_parcours.size(); i++) {
            parcours = list_parcours.get(i);
            String notes = "";
            for (int j = 0; j < parcours.getNoteCollection().size(); j++) {

                test = new ArrayList(parcours.getNoteCollection());
                notes = notes + test.get(j).getTitre() + " , ";
            }

            ss = ss + "<option onclick ='javascript:showParcoursDetail();showItineraireParcoursDetail()' value='" + n + "'>" + n + "eme parcours -> TITRE : " + parcours.getTitre() + "</option>";

         
            n++;
        }
        ss = ss + "</select></form>";
   
        return ss;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "modifierParcours")
    public String modifierParcours(@WebParam(name = "tit") String tit, @WebParam(name = "com") String com, @WebParam(name = "idParcours") int idParcours, @WebParam(name = "idNotes") String idNotes) {

        List<Note> list_note = noteFacade.findAll();
        List<Note> list_note_parcours = new ArrayList<Note>();
        List<Parcours> list_parcours = parcoursFacade.findAll();
        int n = 1;
        Note note = new Note();
        String[] ids = idNotes.split(",");
        for (int i = 0; i < list_note.size(); i++) {
            for (int j = 0; j < ids.length; j++) {
                if (!ids[j].isEmpty()) {
                    if (list_note.get(i).getIdNote() == Integer.parseInt(ids[j])) {
                        note = list_note.get(i);
                        list_note_parcours.add(note);
                        break;
                    }
                }
            }
        }
        Parcours parcours2 = parcoursFacade.find(idParcours);
        parcours2.setTitre(tit);
        parcours2.setCommentaire(com);
        parcours2.setNoteCollection(list_note_parcours);
        parcoursFacade.edit(parcours2);
        return "Vous avez bien modifié et enregistré le parcours : " + tit + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "showParcoursDetail")
    public String showParcoursDetail(@WebParam(name = "idParcours") int idParcours) {
        //TODO write your implementation code here:
         Parcours parcours = parcoursFacade.find(idParcours);

        List<Note> test;

        String tt = "<table id='showparcoursDetail' style='background:#FFF0F5; border-color:#98bf21' border='2'  width='auto'><thead><tr><th>Num</th><th>Titre</th><th>Commentaire</th><th>Notes</th></tr></thead><tbody>";    
      int n=1;
            String notes = "";
            for (int j = 0; j < parcours.getNoteCollection().size(); j++) {
                test = new ArrayList(parcours.getNoteCollection());
                notes = notes + n+"." +test.get(j).getTitre() + " @ " +test.get(j).getAdresse() +"</br>";
                n++;
            }
            tt = tt + "<tr><td>" + parcours.getIdParcours() + "</td><td>" + parcours.getTitre() + "</td><td>" + parcours.getCommentaire() + "</td><td>" + notes + "</td></tr>";

        
        tt = tt + "</tbody></table>";
        return tt;
    }

  
}
