package br.ime.usp.commendans.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Customer {
    
    @Id @GeneratedValue
    private Long id;
    
    private Long appId;

    @ManyToMany()
    private List<Item> items;
    
    @ManyToOne
    private Application app;
    
    @Deprecated
    public Customer() {
    }

    public Customer(List<Item> items, Long appId, Application app) {
        this.items = items;
        this.appId = appId;
        this.app = app;
    }

    public List<Item> itemsBought() {
        return items;
    }
    
    @Override
    public String toString() {
        return "User " + appId + " from " + app;
    }

    public void add(Item item) {
        items.add(item);
    }
    
    public Application getApp() {
        return app;
    }
    
}
