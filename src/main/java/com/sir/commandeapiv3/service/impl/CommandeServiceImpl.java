/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.commandeapiv3.service.impl;

import com.sir.commandeapiv3.bean.Commande;
import com.sir.commandeapiv3.bean.CommandeItem;
import com.sir.commandeapiv3.dao.CommandeDao;
import com.sir.commandeapiv3.service.CommandeItemService;
import com.sir.commandeapiv3.service.CommandeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeItemService commandeItemService;
    @Autowired
    private CommandeDao commandeDao;

    @Override
    public Commande findByReference(String reference) {
        return commandeDao.findByReference(reference);
    }

    @Override
    public int payer(String reference, double montant) {
        Commande commande = findByReference(reference);
        if (commande == null) {
            return -1;
        } else if (commande.getTotalPaiement() + montant > commande.getTotal()) {
            return -2;
        } else {
            double nvPaiement = commande.getTotalPaiement() + montant;
            commande.setTotalPaiement(nvPaiement);
            commandeDao.save(commande);
            return 1;
        }
    }

    public CommandeDao getCommandeDao() {
        return commandeDao;
    }

    public void setCommandeDao(CommandeDao commandeDao) {
        this.commandeDao = commandeDao;
    }

//    @Override
//    public int creer(Commande commande) {
//        Commande c = findByReference(commande.getReference());
//        if (c != null) {
//            return -1;
//        } else if (commande.getCommandeItems().isEmpty()) {
//            return -2;
//        } else {
//            System.out.println(commande);
//            commande.setTotalPaiement(0);
//            commandeDao.save(commande);
//            double total = 0;
//            for (CommandeItem commandeItem : commande.getCommandeItems()) {
//                commandeItem.setCommande(commande);
//                commandeItemService.creerCommmadeItem(commandeItem);
//                total = commandeItem.getPrix() * commandeItem.getQuantite();
//            }
//            commande.setTotal(total);
//            commandeDao.save(commande);
//            return 1;
//        }
    @Override
    public int creer(Commande commande) {
        Commande c = findByReference(commande.getReference());
        if (c != null) {
            return -1;
        } else {
            c = new Commande();
            c.setReference(commande.getReference());
            c.setTotalPaiement(0);
            commandeDao.save(c);
            double total = 0;

            for (CommandeItem commandeItem : commande.getCommandeItems()) {
                commandeItem.setCommande(c);
                commandeItemService.creerCommmadeItem(commandeItem);
                total = commandeItem.getPrix() * commandeItem.getQuantite();

            }
            System.out.println("ha zaiciazndzandazaz" + total);
            c.setTotal(total);

            commandeDao.save(c);
            return 1;

        }

    }

    @Override
    public List<Commande> findByTotal(double total) {
        return commandeDao.findByTotal(total);
    }

    public CommandeItemService getCommandeItemService() {
        return commandeItemService;
    }

    public void setCommandeItemService(CommandeItemService commandeItemService) {
        this.commandeItemService = commandeItemService;
    }

}
