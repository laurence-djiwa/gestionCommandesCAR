package com.example.demo.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommandeRepository extends CrudRepository<Commande, Integer>{
    List<Commande> findByClientEmail(String email);
}


