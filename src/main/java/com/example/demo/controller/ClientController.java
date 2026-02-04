package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import jakarta.servlet.http.HttpSession;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import java.util.*;

@Controller
@RequestMapping("/store/client")
public class ClientController {

        private final ClientService cs;

        public ClientController(ClientService cli) {
            this.cs = cli;
        }

        //Afficher le formulaire d'inscription
        @GetMapping("/inscription") //affiché dans l'url
        public ModelAndView formulaireInscription() {
            return new ModelAndView("clients/inscription"); //la vue ==> fichier html
        }

        //Traitement de l'inscription
        @PostMapping("/inscription")
        public RedirectView inscription(@RequestParam String email,   //c'est pour dire que cette méthode récupère les champs du formulaire qui sera envoyé lorsque l'user clique sur Se connecter
                                        @RequestParam String motdepasse,
                                        @RequestParam String nom,
                                        @RequestParam String prenom) {

            Client C = new Client(email, motdepasse, nom, prenom);
            cs.inscrire(C);

            return new RedirectView("/store/home");
        }

        //Afficher le formulaire de connexion
        @GetMapping("/connexion")
        public ModelAndView formulaireConnexion() {
            return new ModelAndView("clients/connexion");
        }

        //Traitement de la connexion
        @PostMapping("/connexion")
        public ModelAndView connexion(@RequestParam String email, @RequestParam String motdepasse, HttpSession session) {

            Optional<Client> client= cs.connecter(email, motdepasse);

            if (client.isPresent()) {
                session.setAttribute("client", client);
                return new ModelAndView(new RedirectView("/store/home"));
            }
            ModelAndView mw = new ModelAndView("clients/inscription");
            mw.addObject("error", "Email ou mot de passe incorrect");
            return mw;
        }

        //Déconnexion
        @GetMapping("/deconnexion")
        public RedirectView deconnexion(HttpSession session) {
            session.invalidate();
            return new RedirectView("/store/home");
        }




    }



