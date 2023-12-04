package sn.ept.git.dic2.ventevelosandroid.models;

import java.io.Serializable;

public class Stock implements Serializable {
    private Magasin magasin;
    private Produit produit;
    private int quantite;

    // Constructeurs, getters, setters, etc.

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
