package br.ime.usp.commendans.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.ime.usp.commendans.itemtoitem.ItemVector;
import br.ime.usp.commendans.itemtoitem.SingleAppRecommender;

@Entity
public class Application {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String accessKey;

    @Transient
    private SingleAppRecommender recommender;

    @Deprecated
    protected Application() {
    }

    public Application(String name, String accessKey) {
        this.name = name;
        this.accessKey = accessKey;
    }

    public String getName() {
        return name;
    }

    public void use(SingleAppRecommender recommender) {
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
}
