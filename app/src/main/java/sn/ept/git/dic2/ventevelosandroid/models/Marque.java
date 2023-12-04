package sn.ept.git.dic2.ventevelosandroid.models;

import java.io.Serializable;

public class Marque implements Serializable {
    Long id;
    String nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
