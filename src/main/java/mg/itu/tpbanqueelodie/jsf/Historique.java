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
import mg.itu.tpbanqueelodie.entity.OperationBancaire;
import mg.itu.tpbanqueelodie.service.GestionnaireCompte;

/**
 *
 * @author andri
 */
@Named(value = "historique")
@ViewScoped
public class Historique implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private List<OperationBancaire> listeOperations;

    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    /**
     * Creates a new instance of Historique
     */
    public Historique() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public List<OperationBancaire> getListeOperations() {
        return listeOperations;
    }

    public String afficher() {
        return "historique?id=" + id + "&amp;faces-redirect=true";
    }
    
    public CompteBancaire getCompteBancaireById(){
        compte =  gestionnaireCompte.findById(id);
        return compte;
    }
    
    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
        if (compte != null) {
                listeOperations = compte.getOperations();
            }
    }
    
}
