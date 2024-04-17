/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueelodie.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanqueelodie.entity.CompteBancaire;
import mg.itu.tpbanqueelodie.jsf.util.Util;
import mg.itu.tpbanqueelodie.service.GestionnaireCompte;

/**
 *
 * @author andri
 */
@Named(value = "modifieCompte")
@ViewScoped
public class ModifieCompte implements Serializable {

    private Long id;
    private String nouveauNom;
    private CompteBancaire compte;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of ModifieCompte
     */
    public ModifieCompte() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNouveauNom() {
        return nouveauNom;
    }

    public void setNouveauNom(String nouveauNom) {
        this.nouveauNom = nouveauNom;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
    }

    public String modifieNom() {
        compte = gestionnaireCompte.findById(id);
        String ancienne = compte.getNom();

        if (nouveauNom != null) {
            compte.setNom(nouveauNom);
            gestionnaireCompte.update(compte);
            Util.addFlashInfoMessage("Mouvement enregistré (Retrait) sur compte de " + ancienne + " en "+compte.getNom());

        } else {
            Util.messageErreur("Saisir un nom dans le champ disponnilble", "Saisir un nom dans le champ disponnilble", "form:compte");

        }
        return "listeComptes?faces-redirect=true";
    }

}
