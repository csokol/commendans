package br.ime.usp.commendans.recommender;

import java.util.List;

import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.recommender.itemtoitem.ItemVector;

public interface Recommender {
    public ItemVector recommendedItemsFor(Item item);

    public ItemVector recommendedItemsFor(List<Item> items);
}
