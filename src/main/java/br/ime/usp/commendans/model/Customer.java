package br.ime.usp.commendans.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Customer {
    
    @Id @GeneratedValue
    private Long id;

    @ManyToMany
    private List<Item> items;
    
    @Deprecated
    public Customer() {
    }

    public Customer(List<Item> items, Long id) {
        this.items = items;
        this.id = id;
    }

    public List<Item> itemsBought() {
        return items;
    }
    
    @Override
    public String toString() {
        return "User " + id;
    }

    public void add(Item item) {
        items.add(item);
    }
    
}
