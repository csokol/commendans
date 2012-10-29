package br.ime.usp.commendans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ime.usp.commendans.factory.ItemToItemsAssociation;
import br.ime.usp.commendans.factory.ItemToItemsAssociation.Tuple;
import br.ime.usp.commendans.model.Item;

public class ItemToItemRecommender implements Recommender {

    private final Map<Item, List<Tuple>> associations;

    public ItemToItemRecommender(
            HashMap<Item, ItemToItemsAssociation> associations) {
        this.associations = parse(associations);
    }

    private Map<Item, List<Tuple>> parse(HashMap<Item, ItemToItemsAssociation> associations) {
        HashMap<Item, List<Tuple>> map = new HashMap<Item, List<Tuple>>();
        Set<Item> items = associations.keySet();
        for (Item item : items) {
            ItemToItemsAssociation association = associations.get(item);
            List<Tuple> tuples = association.toTupleList();
            map.put(item, tuples);
        }
        return map;
    }

    @Override
    public List<Tuple> recommendendItemsFor(Item item) {
        List<Tuple> tuples = associations.get(item);
        return tuples;
    }

}
