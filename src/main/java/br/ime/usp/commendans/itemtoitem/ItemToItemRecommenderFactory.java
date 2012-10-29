package br.ime.usp.commendans.itemtoitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.model.User;

public class ItemToItemRecommenderFactory {

    private Map<Item, List<User>> usersByItemBought;
    private HashMap<Item, ItemToItemsAssociation> associations;
    
    public ItemToItemRecommenderFactory() {
        usersByItemBought = new HashMap<Item, List<User>>();
    }
    
    public void addUser(User user) {
        List<Item> items = user.itemsBought();
        for (Item item : items) {
            store(item, user);
        }
    }

    private void store(Item item, User user) {
        List<User> users = usersByItemBought.get(item);
        if (users == null) {
            users = new ArrayList<User>();
        }
        users.add(user);
        usersByItemBought.put(item, users);
    }
    
    
    public ItemToItemRecommender build() {
        associations = new HashMap<Item, ItemToItemsAssociation>();
        Set<Item> items = usersByItemBought.keySet();
        for (Item item : items) {
            ItemToItemsAssociation association = new ItemToItemsAssociation(item);
            List<User> users = usersByItemBought.get(item);
            for (User user : users) {
                List<Item> boughtTogether = user.itemsBought();
                System.out.println(item + " -> " +boughtTogether);
                for (Item i : boughtTogether) {
                    association.associate(i);
                }
            }
            associations.put(item, association);
        }
        return new ItemToItemRecommender(associations);
    }
    
    public HashMap<Item, ItemToItemsAssociation> getAssociations() {
        return associations;
    }

}
