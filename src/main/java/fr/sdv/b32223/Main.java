package fr.sdv.b32223;

import fr.sdv.b32223.PetStore.entites.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static <Address> void main(String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "petstore" );
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();


            // Product
            Product product1 = new Product();
            product1.setCode("P1");
            product1.setLabel("Product 1");
            product1.setType(ProdType.FOOD);
            product1.setPrice(10.99);
            em.persist(product1);

            Product product2 = new Product();
            product2.setCode("P2");
            product2.setLabel("Product 2");
            product2.setType(ProdType.ACCESSORY);
            product2.setPrice(19.99);
            em.persist(product2);

            Product product3 = new Product();
            product3.setCode("P3");
            product3.setLabel("Product 3");
            product3.setType(ProdType.CLEANING);
            product3.setPrice(5.99);
            em.persist(product3);

            // Adress

            Adress adress1 = new Adress();
            adress1.setNumber("123");
            adress1.setStreet("Street 1");
            adress1.setZipCode("12345");
            adress1.setCity("City 1");
            em.persist(adress1);

            Adress adress2 = new Adress();
            adress2.setNumber("456");
            adress2.setStreet("Street 2");
            adress2.setZipCode("67890");
            adress2.setCity("City 2");
            em.persist(adress2);

            Adress adress3 = new Adress();
            adress3.setNumber("789");
            adress3.setStreet("Street 3");
            adress3.setZipCode("54321");
            adress3.setCity("City 3");
            em.persist(adress3);
            em.persist(adress3);

            // PetStore

            PetStore petStore1 = new PetStore();
            petStore1.setName("Pet Store 1");
            petStore1.setManagerName("Manager 1");
            petStore1.setAdress(adress1);
            petStore1.setProducts(new ArrayList<>());
            petStore1.getProducts().add(product1);
            em.persist(petStore1);

            PetStore petStore2 = new PetStore();
            petStore2.setName("Pet Store 2");
            petStore2.setManagerName("Manager 2");
            petStore2.setAdress(adress2);
            petStore2.setProducts(new ArrayList<>());
            petStore2.getProducts().add(product2);
            em.persist(petStore2);

            PetStore petStore3 = new PetStore();
            petStore3.setName("Pet Store 3");
            petStore3.setManagerName("Manager 3");
            petStore3.setAdress(adress3);
            petStore3.setProducts(new ArrayList<>());
            petStore3.getProducts().add(product3);
            em.persist(petStore3);


            Cat cat1 = new Cat();
            cat1.setBirth(new Date());
            cat1.setCouleur("Orange");
            cat1.setChipId("C1");
            cat1.setPetStore(petStore1);
            em.persist(cat1);

            Cat cat2 = new Cat();
            cat2.setBirth(new Date());
            cat2.setCouleur("Gray");
            cat2.setChipId("C2");
            cat2.setPetStore(petStore1);
            em.persist(cat2);

            Cat cat3 = new Cat();
            cat3.setBirth(new Date());
            cat3.setCouleur("Black");
            cat3.setChipId("C3");
            cat3.setPetStore(petStore2);
            em.persist(cat3);


            Fish fish1 = new Fish();
            fish1.setBirth(new Date());
            fish1.setCouleur("Gold");
            fish1.setLivingEnv(FishLivEnv.FRESH_WATER);
            fish1.setPetStore(petStore1);
            em.persist(fish1);

            Fish fish2 = new Fish();
            fish2.setBirth(new Date());
            fish2.setCouleur("Blue");
            fish2.setLivingEnv(FishLivEnv.SEA_WATER);
            fish2.setPetStore(petStore1);
            em.persist(fish2);

            Fish fish3 = new Fish();
            fish3.setBirth(new Date());
            fish3.setCouleur("Red");
            fish3.setLivingEnv(FishLivEnv.SEA_WATER);
            fish3.setPetStore(petStore2);
            em.persist(fish3);


            em.getTransaction().commit();



            Long petStoreId = 1L; // ID de l'animalerie donnée

            // Exécuter la requête pour extraire tous les animaux d'une animalerie donnée
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a JOIN a.petStore p WHERE p.id = :petStoreId", Animal.class);
            query.setParameter("petStoreId", petStoreId);
            List<Animal> animals = query.getResultList();

            // Afficher les animaux
            for (Animal animal : animals) {
                System.out.println("Animal ID: " + animal.getId());
                System.out.println("Birth Date: " + animal.getBirth());
                System.out.println("Color: " + animal.getCouleur());
                System.out.println();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
