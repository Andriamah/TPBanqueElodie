/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueelodie.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanqueelodie.entity.CompteBancaire;
import mg.itu.tpbanqueelodie.service.GestionnaireCompte;

/**
 *
 * @author andri
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    /**
     * Creates a new instance of CompteBancaireBean
     */
    public ListeComptes() {
    }

    private List<CompteBancaire> listeCompteBancaire;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public List<CompteBancaire> getAllComptes() {
        if (listeCompteBancaire == null) {
            listeCompteBancaire = gestionnaireCompte.getAllComptes();
        }
        return listeCompteBancaire;
    }

}
