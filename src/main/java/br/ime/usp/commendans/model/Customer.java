package br.ime.usp.commendans.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Customer {
    
    @Id @GeneratedValue
    private Long id;
    
    private Long clientAppCustomerId;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Item> items;
    
    @ManyToOne
    private ClientApp app;
    
    @Deprecated
    public Customer() {
    }

    public Customer(List<Item> items, Long clientAppCustomerId, ClientApp app) {
        this.items = items;
        this.clientAppCustomerId = clientAppCustomerId;
        this.app = app;
    }

    public List<Item> itemsBought() {
        return items;
    }
    
    @Override
    public String toString() {
        return "User " + clientAppCustomerId + " from " + app;
    }

    public void add(Item item) {
        items.add(item);
    }
    
    public ClientApp getApp() {
        return app;
    }
    
    public Long getClientAppCustomerId() {
        return clientAppCustomerId;
    }
    
}
