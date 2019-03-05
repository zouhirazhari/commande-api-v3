/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.commandeapiv3.service.impl;

import com.sir.commandeapiv3.bean.Commande;
import com.sir.commandeapiv3.bean.CommandeItem;
import com.sir.commandeapiv3.dao.CommandeDao;
import com.sir.commandeapiv3.dao.CommandeItemDao;
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
public class CommandeItemServiceImpl implements CommandeItemService {

    @Autowired
    private CommandeService commandeService;

    public CommandeService getCommandeService() {
        return commandeService;
    }

    public void setCommandeService(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @Autowired
    private CommandeItemDao commandeItemDao;

    public CommandeItemDao getCommandeItemDao() {
        return commandeItemDao;
    }

    public void setCommandeItemDao(CommandeItemDao commandeItemDao) {
        this.commandeItemDao = commandeItemDao;
    }

    @Override
    public List<CommandeItem> findByCommandeReference(String reference) {
        return commandeItemDao.findByCommandeReference(reference);
    }

    @Override
    public List<CommandeItem> findByPrix(double prix) {
        return commandeItemDao.findByPrix(prix);
    }

    @Override
    public int creerCommmadeItem(CommandeItem commandeItem) {
        Commande commande = commandeService.findByReference(commandeItem.getCommande().getReference());
        if (commande == null) {
            return -1;

        } else {
            //          CommandeItem c = new CommandeItem();
//            c.setCommande(commande);
//            c.setRefProduit(commandeItem.getRefProduit());
//            c.setQuantite(commandeItem.getQuantite());
//            c.setPrix(commandeItem.getPrix());
            commandeItemDao.save(commandeItem);
            return 1;

        }

    }
}
