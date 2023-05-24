package fr.sdv.b32223.PetStore.entites;

import javax.persistence.*;

@Entity
public class Fish extends Animal {
   @Column(name = "livingEnv")
    @Enumerated(EnumType.STRING)
    private FishLivEnv livingEnv;

    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }

    // Constructeurs, getters et setters
}
