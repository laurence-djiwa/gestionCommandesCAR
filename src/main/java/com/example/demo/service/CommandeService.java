package com.example.demo.service;

import com.example.demo.entity.Commande;
import com.example.demo.entity.CommandeRepository;
import com.example.demo.entity.LigneCommande;
import com.example.demo.entity.LigneCommandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository commanderepo;
    private final LigneCommandeRepository ligneRepo;

    public CommandeService(CommandeRepository crepo, LigneCommandeRepository ligneRepo){
        this.commanderepo =crepo;
        this.ligneRepo = ligneRepo;
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

    public void ajouterLigne(Integer commandeId, String libelle, int quantite, double pu) {
        Commande commande = commanderepo.findById(commandeId).orElseThrow();

        LigneCommande ligne = new LigneCommande(libelle, quantite, pu, commande);
        commande.ajouterLigne(ligne);

        commanderepo.save(commande);
    }

    public void supprimerLigne(Integer ligneId) {
        ligneRepo.deleteById(ligneId);
    }

    public Commande trouverCommande(Integer id){
        return commanderepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande inexistante"));
    }
}
