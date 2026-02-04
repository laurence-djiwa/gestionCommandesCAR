package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idligne;
    private String libelle;
    private int quantite;
    private double pu;

    @ManyToOne
    private Commande commande;

    public LigneCommande(String libelle, int quantite, double pu, Commande commande){
        this.libelle = libelle;
        this.quantite = quantite;
        this.pu = pu;
        this.commande = commande;

    }

    public LigneCommande(){

    }

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Commande getCommande() {
        return commande;
    }
}
