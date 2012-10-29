package br.ime.usp.commendans;

import java.util.List;

import br.ime.usp.commendans.factory.ItemToItemsAssociation.Tuple;
import br.ime.usp.commendans.model.Item;

public interface Recommender {
    public List<Tuple> recommendendItemsFor(Item item);
}
