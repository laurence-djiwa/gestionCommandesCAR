package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> lignes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_email") // lien vers client
    private Client client;

    public Commande(){
    }

    public Integer getId(){
        return id;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void ajouterLigne(LigneCommande ligne){
        lignes.add(ligne);
        ligne.setCommande(this);
        
    }

    public double getTotalCommande(){
        double totalCommande = 0.0;
        for (LigneCommande ligne : lignes){
            totalCommande += ligne.getPrixTotal();
        }
        return totalCommande;
    }
    

}
