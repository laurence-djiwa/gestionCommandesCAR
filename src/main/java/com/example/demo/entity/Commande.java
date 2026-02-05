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

    public Commande(){
    }

    public Integer getId(){
        return id;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
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
