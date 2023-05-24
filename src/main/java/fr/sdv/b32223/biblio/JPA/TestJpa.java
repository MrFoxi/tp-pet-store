package fr.sdv.b32223.biblio.JPA;

import fr.sdv.b32223.biblio.entites.Livre;

import javax.persistence.*;
import java.util.ArrayList;

public class TestJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp1");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        /* Insere nouveau livre */
        Livre l1 = new Livre();
        l1.setTitre("La vie");
        l1.setAuteur("méli");
        em.persist(l1);

        /* Trouve le livre numero 5 et modifer son titre */
        Livre l2 = em.find(Livre.class, 5);
        if (l2 != null){
            l2.setTitre("Du plaisir dans la cuisine");
            System.out.println(l2);
        }

        /* Trouver livre avec son titre */
        TypedQuery<Livre> livreTitre = em.createQuery("select l3 from Livre l3 where l3.titre='Vingt mille lieues sous les mers'", Livre.class);
        Livre l3 = livreTitre.getResultList().get(0);
        if (l3 != null){
            System.out.println(l3);
        }

        /* Trouver livre avec son auteur */
        TypedQuery<Livre> livreAuteur = em.createQuery("select l4 from Livre l4 where l4.auteur='Emile Zola'", Livre.class);
        Livre l4 = livreAuteur.getResultList().get(0);
        if (l4 != null){
            System.out.println(l4);
        }

        /* Supprime livre */
        Livre l5 = em.find(Livre.class, 6);
        if (l5 != null){
            em.remove(l5);
        }

        /* Affiche tous les livres */
        TypedQuery<Livre> livres = em.createQuery("select l6 from Livre l6 ", Livre.class);
        ArrayList<Livre> list= (ArrayList<Livre>) livres.getResultList();
        if (!list.isEmpty()) {
            for (Livre liv : list) {
                System.out.println(liv);
            }
        }

        transaction.begin();
        transaction.commit();
        em.close();
        emf.close();
    }
}
