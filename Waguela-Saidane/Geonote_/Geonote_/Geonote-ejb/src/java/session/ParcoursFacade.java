/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Parcours;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lin
 */
@Stateless
public class ParcoursFacade extends AbstractFacade<Parcours> {
    @PersistenceContext(unitName = "Geonote-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParcoursFacade() {
        super(Parcours.class);
    }
    
}
