package com.example.demo.controller;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
import com.example.demo.service.CommandeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
