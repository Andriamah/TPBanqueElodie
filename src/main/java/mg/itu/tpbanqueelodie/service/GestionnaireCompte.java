/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueelodie.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Observes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import mg.itu.tpbanqueelodie.entity.CompteBancaire;

/**
 *
 * @author andri
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "elodieuser", // nom et
        password = "1215elo!", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        List<CompteBancaire> listInit = new ArrayList<>();
        listInit.add(new CompteBancaire("John Lennon", 150000));
        listInit.add(new CompteBancaire("Paul McCartney", 950000));
        listInit.add(new CompteBancaire("Ringo Starr", 20000));
        listInit.add(new CompteBancaire("George Harrison", 100000));

        Long nbCompte = this.nbComptes();
        if (nbCompte == 0) {
            for (CompteBancaire compte : listInit) {
                this.creerCompte(compte);
            }
        } else {
            System.out.println("Init Failed");
        }
    }

    public Long nbComptes() {
        TypedQuery<Long> query = em.createNamedQuery("CompteBancaire.countAll", Long.class);
        Long nb = query.getSingleResult();
        return nb;
    }

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        List<CompteBancaire> liste = query.getResultList();
        return liste;

    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }

    public CompteBancaire findById(Long idCompte) {
        return em.find(CompteBancaire.class, idCompte);
    }
}
