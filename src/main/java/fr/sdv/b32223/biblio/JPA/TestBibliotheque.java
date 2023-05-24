package fr.sdv.b32223.biblio.JPA;

import fr.sdv.b32223.biblio.entites.Client;
import fr.sdv.b32223.biblio.entites.Emprunt;
import fr.sdv.b32223.biblio.entites.Livre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

public class TestBibliotheque {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp1");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();


        /* Trouver emprunt et sa liste de livre avec son id */
        TypedQuery<Emprunt> emprunt = em.createQuery("select e1 from Emprunt e1 where e1.id=1", Emprunt.class);
        Emprunt e1 = emprunt.getResultList().get(0);
        if (e1 != null){
            System.out.println("----- Emprunt -----");
            System.out.println(e1);
            System.out.println("----- Liste des livres correspondants -----");
            Set<Livre> listeLivre = e1.getLivres();
            if (!listeLivre.isEmpty()) {
                for (Livre liv : listeLivre) {
                    System.out.println(liv);
                }
            }
        }

        /* Liste des emprunts d'un client donné */
        Client c1 = em.find(Client.class, 1);
        TypedQuery<Emprunt> emprunts = em.createQuery("select e2 from Emprunt e2 where e2.idClient= :idClient", Emprunt.class);
        emprunts.setParameter("idClient", c1);
        ArrayList<Emprunt> listeEmprunt = (ArrayList<Emprunt>) emprunts.getResultList();
        if (!listeEmprunt.isEmpty()) {
            System.out.println("----- Client -----");
            System.out.println(c1);
            System.out.println("----- Liste de ses emprunts -----");
            for (Emprunt emp : listeEmprunt) {
                System.out.println(emp);
            }
        }

        transaction.begin();
        transaction.commit();
        em.close();
        emf.close();
    }
}
