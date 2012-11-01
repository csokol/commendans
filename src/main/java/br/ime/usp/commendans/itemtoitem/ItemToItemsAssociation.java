package br.ime.usp.commendans.itemtoitem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ime.usp.commendans.model.Item;

public class ItemToItemsAssociation {
    private Item item;
    private Map<Item, Double> associations;

    public ItemToItemsAssociation(Item item) {
        this.item = item;
        this.associations = new HashMap<Item, Double>();
    }

    public void associate(Item i) {
        if (i.equals(this.item))
            return;
        Double v = associations.get(i);
        if (v == null) {
            v = 0.0;
        }
        v += 1.0;
        associations.put(i, v);
    }
    
    public double associationValue(Item i) {
        Double v = associations.get(i);
        if (v == null) {
            return 0.0;
        }
        return v;
    }
    
    
    @Override
    public String toString() {
        String res = this.item.toString() + " => {" + associations.toString() + "}";
        return res;
    }

    public List<Tuple> toTupleList() {
        Set<Item> items = associations.keySet();
        ArrayList<Tuple> tuples = new ArrayList<Tuple>();
        for (Item item : items) {
            Double value = associations.get(item);
            tuples.add(new Tuple(item, value));
        }
        Collections.sort(tuples);
        Collections.reverse(tuples);
        return tuples;
    }

}
