package br.ime.usp.commendans.itemtoitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ime.usp.commendans.Recommender;
import br.ime.usp.commendans.model.Item;

public class ItemToItemRecommender implements Recommender {

    private final Map<Item, ItemVector> associations;

    public ItemToItemRecommender(
            HashMap<Item, ItemToItemsAssociation> associations) {
        this.associations = parse(associations);
    }

    private Map<Item, ItemVector> parse(HashMap<Item, ItemToItemsAssociation> associations) {
        HashMap<Item, ItemVector> map = new HashMap<Item, ItemVector>();
        Set<Item> items = associations.keySet();
        for (Item item : items) {
            ItemToItemsAssociation association = associations.get(item);
            List<Tuple> tuples = association.toTupleList();
            map.put(item, new ItemVector(tuples));
        }
        return map;
    }

    @Override
    public ItemVector recommendendItemsFor(Item item) {
        return associations.get(item);
    }

    public ItemVector recommendendItemsFor(List<Item> items) {
        ItemVector vector = new ItemVector(new ArrayList<Tuple>());
        for (Item item : items) {
            ItemVector other = recommendendItemsFor(item);
            vector = vector.merge(other);
        }
        return vector;
    }

}
