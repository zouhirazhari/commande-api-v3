/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sir.commandeapiv3.service;

import com.sir.commandeapiv3.bean.Commande;
import com.sir.commandeapiv3.bean.CommandeItem;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface CommandeItemService {

    public int creerCommmadeItem(CommandeItem commandeItem);
    public List<CommandeItem> findByCommandeReference(String reference);
    public List<CommandeItem> findByPrix(double prix);
    //public List<CommandeItem> findItemByC

}
