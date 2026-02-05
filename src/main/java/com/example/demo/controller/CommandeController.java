package com.example.demo.controller;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
import com.example.demo.service.CommandeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/store/commande")
public class CommandeController {

    private CommandeService cs;

    public CommandeController(CommandeService cs){
        this.cs = cs;
    }

    @GetMapping("/creation")
    public ModelAndView formCommande(){
        return new ModelAndView("commandes/creation");
    }

    @PostMapping("/creation")
    public RedirectView creationCommande(@RequestParam String libelle,
                                         @RequestParam Integer quantite,
                                         @RequestParam double pu){

        cs.creerUneLigneDeCommande(libelle, quantite, pu);
        return new RedirectView("/store/commande");
    }

    @GetMapping
    public ModelAndView listeCommandes() {
        ModelAndView mv = new ModelAndView("commandes/affichage");
        mv.addObject("commande", cs.commandesDuClient());
        return mv;
    }

    @PostMapping("/{id}/ajouter-ligne")
    public RedirectView ajouterLigne(@PathVariable Integer id,
                                     @RequestParam String libelle,
                                     @RequestParam int quantite,
                                     @RequestParam double pu) {
        cs.ajouterLigne(id, libelle, quantite, pu);
        return new RedirectView("/store/commande");
    }

    @PostMapping("/ligne/{id}/supprimer")       //{id} est une variable d'url
    public RedirectView supprimerLigne(@PathVariable Integer id) {  //Prends la valeur présente dans l’URL à l’emplacement {id}
                                                                        //et mets-la dans la variable Java id (qui n'a rien à voir avec les attributs d'un champ
        cs.supprimerLigne(id);
        return new RedirectView("/store/commande");
    }
}
