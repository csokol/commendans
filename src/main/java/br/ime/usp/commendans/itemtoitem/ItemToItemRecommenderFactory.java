package br.ime.usp.commendans.itemtoitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.model.Customer;

@Component
public class ItemToItemRecommenderFactory {

    private Map<Item, List<Customer>> usersByItemBought;
    private HashMap<Item, ItemToItemsAssociation> associations;
    
    public ItemToItemRecommenderFactory() {
        usersByItemBought = new HashMap<Item, List<Customer>>();
    }
    
    public void addUser(Customer user) {
        List<Item> items = user.itemsBought();
        for (Item item : items) {
            store(item, user);
        }
    }

    private void store(Item item, Customer user) {
        List<Customer> users = usersByItemBought.get(item);
        if (users == null) {
            users = new ArrayList<Customer>();
        }
        users.add(user);
        usersByItemBought.put(item, users);
    }
    
    
    public ItemToItemRecommender build() {
        associations = new HashMap<Item, ItemToItemsAssociation>();
        Set<Item> items = usersByItemBought.keySet();
        for (Item item : items) {
            ItemToItemsAssociation association = new ItemToItemsAssociation(item);
            List<Customer> users = usersByItemBought.get(item);
            for (Customer user : users) {
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
