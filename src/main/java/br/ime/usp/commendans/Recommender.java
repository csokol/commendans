package br.ime.usp.commendans;

import br.ime.usp.commendans.itemtoitem.ItemVector;
import br.ime.usp.commendans.model.Item;

public interface Recommender {
    public ItemVector recommendedItemsFor(Item item);
}
