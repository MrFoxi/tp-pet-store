package fr.sdv.b32223.PetStore.entites;

import javax.persistence.*;

@Entity
public class Cat extends Animal {
    @Column(name = "chipId")
    private String chipId;

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }

    // Constructeurs, getters et setters
}