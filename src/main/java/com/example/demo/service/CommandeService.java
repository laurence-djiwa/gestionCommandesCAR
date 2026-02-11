package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.producer.CommandeProducer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository commanderepo;
    private final LigneCommandeRepository ligneRepo;
    private final CommandeProducer producer;

    public CommandeService(CommandeRepository crepo, LigneCommandeRepository ligneRepo, CommandeProducer producer){
        this.commanderepo =crepo;
        this.ligneRepo = ligneRepo;
        this.producer = producer;
    }

    /*public Commande creerCommande(){

        Commande C = new Commande();
        commanderepo.save(C);

        return C;
    }*/

    /*
    public Commande creerUneLigneDeCommande (String libelle, int quantite, double pu){

        Commande commande = new Commande();

        LigneCommande ligne = new LigneCommande(libelle, quantite, pu, commande);
        commande.ajouterLigne(ligne);
        commanderepo.save(commande);
        return commande;
    }*/
    public Commande creerCommandePourClient(Client client) {
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setStatut("En Attente");
        return commanderepo.save(commande);
    }


    /*
    public Iterable<Commande> commandesDuClient() { //pour afficher
        return commanderepo.findAll();
    }*/

    public List<Commande> commandesDuClient(String email) {
        return commanderepo.findByClientEmail(email);
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

    public void soumettreCommande(Integer id) {
        Commande commande = commanderepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Commande introuvable"));

        if (!"EN_ATTENTE".equals(commande.getStatut())) {
            return;
        }

        // changer le statut
        commande.setStatut("Envoyee");
        commanderepo.save(commande);

        // envoyer le message Kafka

        for (LigneCommande ligne : commande.getLignes()) {
            String message = ligne.getIdLigne() + "," + ligne.getQuantite();
            producer.envoyerCommande(message);
        }
    }

}
