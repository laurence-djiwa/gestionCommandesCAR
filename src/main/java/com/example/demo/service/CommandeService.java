package com.example.demo.service;

import com.example.demo.entity.Commande;
import com.example.demo.entity.CommandeRepository;
import com.example.demo.entity.LigneCommande;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private CommandeRepository commanderepo;

    public CommandeService(CommandeRepository crepo){
        this.commanderepo =crepo;
    }

    /*public Commande creerCommande(){

        Commande C = new Commande();
        commanderepo.save(C);

        return C;
    }*/

    public Commande creerUneLigneDeCommande (String libelle, int quantite, double pu){

        Commande commande = new Commande();

        LigneCommande ligne = new LigneCommande(libelle, quantite, pu, commande);
        commande.ajouterLigne(ligne);
        commanderepo.save(commande);
        return commande;
    }

    public Iterable<Commande> commandesDuClient() { //pour afficher
        return commanderepo.findAll();
    }
}
