package br.ime.usp.commendans.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.ime.usp.commendans.recommender.Recommender;
import br.ime.usp.commendans.recommender.itemtoitem.ItemVector;

@Entity
public class ClientApp {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique=true)
    private String accessKey;

    @Transient
    private Recommender recommender;

    @Deprecated
    protected ClientApp() {
    }

    public ClientApp(String name, String accessKey) {
        this.name = name;
        this.accessKey = accessKey;
    }

    public String getName() {
        return name;
    }

    public void use(Recommender recommender) {
        this.recommender = recommender;
    }

    public Long getId() {
        return id;
    }

    public ItemVector recommendedItemsFor(Item item) {
        return recommender.recommendedItemsFor(item);
    }

    public ItemVector recommendedItemsFor(List<Item> items) {
        return recommender.recommendedItemsFor(items);
    }

    public String getAccessKey() {
        return accessKey;
    }

    @Override
    public String toString() {
        return "Application [id=" + id + ", name=" + name + ", accessKey="
                + accessKey + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClientApp other = (ClientApp) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
