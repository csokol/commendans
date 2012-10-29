package br.ime.usp.commendans.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    private Long id;

    @Deprecated
    protected Item() {
    }
    
    public Item(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item other = (Item) obj;
            return other.getId().equals(this.id);
        }
        return false;
    }
    
    public Long getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Item " + id;
    }
}
