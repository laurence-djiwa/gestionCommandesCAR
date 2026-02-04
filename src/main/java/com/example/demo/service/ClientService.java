package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.entity.ClientRepository;

@Service
public class ClientService {



        private ClientRepository clientrepo;

        public ClientService(ClientRepository clientrepo) {
            this.clientrepo = clientrepo;
        }

        public void inscrire (Client C) {
            clientrepo.save(C);
        }

        public Optional<Client> connecter ( String email, String mdp) {

            Optional<Client> C = clientrepo.findById(email);

            if(C.isPresent() && C.get().getMdp().equals(mdp)) {
                return C;
            }
            return Optional.empty();

        }



    }


