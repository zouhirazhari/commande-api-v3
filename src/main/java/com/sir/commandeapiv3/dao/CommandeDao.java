/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.commandeapiv3.dao;

import com.sir.commandeapiv3.bean.Commande;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface CommandeDao extends JpaRepository<Commande, Long> {

    public Commande findByReference(String reference);
    public List<Commande> findByTotal(double total);
    
}
