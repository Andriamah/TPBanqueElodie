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
import mg.itu.tpbanqueelodie.service.GestionnaireCompte;

/**
 *
 * @author andri
 */
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {

    private int idExpediteur;
    private int idBeneficiaire;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }

    public int getIdExpediteur() {
        return idExpediteur;
    }

    public void setIdExpediteur(int idExpediteur) {
        this.idExpediteur = idExpediteur;
    }

    public int getIdBeneficiaire() {
        return idBeneficiaire;
    }

    public void setIdBeneficiaire(int idBeneficiaire) {
        this.idBeneficiaire = idBeneficiaire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String transfertArgent() {
        CompteBancaire c1 = gestionnaireCompte.findById(Long.valueOf(idExpediteur));
        CompteBancaire c2 = gestionnaireCompte.findById(Long.valueOf(idBeneficiaire));
        gestionnaireCompte.transferer(c1, c2, montant);
        return "listeComptes";

    }
}
