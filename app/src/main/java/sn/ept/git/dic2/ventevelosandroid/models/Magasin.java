package sn.ept.git.dic2.ventevelosandroid.models;

public class Magasin {
    private Long id;
    private String nom;
    private String telephone;
    private String email;
    private String adresse;
    private String ville;
    private String etat;
    private String code_zip;

    // Constructeurs, getters, setters, etc.

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCode_zip() {
        return code_zip;
    }

    public void setCode_zip(String code_zip) {
        this.code_zip = code_zip;
    }
}
