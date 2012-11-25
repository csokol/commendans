package br.ime.usp.commendans.itemtoitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.Customer;
import br.ime.usp.commendans.model.Item;

@Component @ApplicationScoped
public class SingleAppRecommenderFactory {

    private Map<Item, List<Customer>> customerByItemBought;
    private HashMap<Item, ItemToItemsAssociation> associations;
    
    public SingleAppRecommenderFactory() {
        customerByItemBought = new HashMap<Item, List<Customer>>();
    }
    
    public void addUser(Customer user) {
        for (Item item : user.itemsBought()) {
            store(item, user);
        }
    }

    private void store(Item item, Customer user) {
        List<Customer> users = customerByItemBought.get(item);
        if (users == null) {
            users = new ArrayList<Customer>();
        }
        users.add(user);
        customerByItemBought.put(item, users);
    }
    
    
    public SingleAppRecommender build() {
        associations = new HashMap<Item, ItemToItemsAssociation>();
        Set<Item> items = customerByItemBought.keySet();
        for (Item item : items) {
            associate(item);
        }
        return new SingleAppRecommender(associations);
    }

    private void associate(Item item) {
        ItemToItemsAssociation association = new ItemToItemsAssociation(item);
        List<Customer> customers = customerByItemBought.get(item);
        for (Customer user : customers) {
            List<Item> boughtTogether = user.itemsBought();
            for (Item i : boughtTogether) {
                association.associate(i);
            }
        }
        associations.put(item, association);
    }
    
    public HashMap<Item, ItemToItemsAssociation> getAssociations() {
        return associations;
    }

    public void clean() {
        customerByItemBought = new HashMap<Item, List<Customer>>();
    }

}
