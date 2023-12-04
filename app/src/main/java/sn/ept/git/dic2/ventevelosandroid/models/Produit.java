package sn.ept.git.dic2.ventevelosandroid.models;

import java.io.Serializable;

public class Produit implements Serializable {
    Long id;
    String nom;
    Categorie categorie;
    Marque marque;
    Long annee_model;
    Double prix_depart;

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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Long getAnnee_model() {
        return annee_model;
    }

    public void setAnnee_model(Long annee_model) {
        this.annee_model = annee_model;
    }

    public Double getPrix_depart() {
        return prix_depart;
    }

    public void setPrix_depart(Double prix_depart) {
        this.prix_depart = prix_depart;
    }
}
