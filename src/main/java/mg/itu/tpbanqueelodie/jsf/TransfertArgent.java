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
//        CompteBancaire c1 = gestionnaireCompte.findById(Long.valueOf(idExpediteur));
//        CompteBancaire c2 = gestionnaireCompte.findById(Long.valueOf(idBeneficiaire));
//        gestionnaireCompte.transferer(c1, c2, montant);
//        return "listeComptes";

        CompteBancaire c1 = gestionnaireCompte.findById(Long.valueOf(idExpediteur));
        CompteBancaire c2 = gestionnaireCompte.findById(Long.valueOf(idBeneficiaire));
        String next = null;
        boolean retour = this.validationdetransfertArgent(c1, c2);
        if (retour) {
            gestionnaireCompte.transferer(c1, c2, montant);
            // Message de succès ; addFlash à cause de la redirection.
            // ...Complétez pour faire apparaitre le montant et les noms des 2 propriétaires des comptes.
            next = "listeComptes?faces-redirect=true"
;
            Util.addFlashInfoMessage("Transfert correctement effectué par " + c1.getNom() + " vers " + c2.getNom() + " de valeur " + montant);
        }

        return next;
    }

    public boolean validationdetransfertArgent(CompteBancaire c1, CompteBancaire c2) {
        boolean retour = false;
        if (c1 == null && c2 == null) {
            Util.messageErreur("Aucun compte avec cet id Expediteur et Beneficiaire", "Aucun compte avec cet id Expediteur et Beneficiaire", "form:c1");
        } else {
            if (c1 == null) {
                Util.messageErreur("Aucun compte avec cet id Expediteur", "Aucun compte avec cet id Expediteur", "form:c1");

            }
            if (c2 == null) {
                Util.messageErreur("Aucun compte avec cet id Beneficiaire", "Aucun compte avec cet id Beneficiaire", "form:c1");

            } else {
                if (c1.getSolde() < montant) { // à compléter pour le cas où le solde du compte source est insuffisant...
                    Util.messageErreur("Montant supérieur à celui de l'expediteur", "Montant supérieur à celui de l'expediteur", "form:c1");

                } else {
                    retour = true;

                }
            }
        }
        return retour;
    }
}
