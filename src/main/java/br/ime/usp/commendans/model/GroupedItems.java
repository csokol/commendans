package br.ime.usp.commendans.model;

import java.util.ArrayList;
import java.util.List;

public class GroupedItems {

    private final List<Item> items;

    public GroupedItems(List<Item> items) {
        this.items = new ArrayList(items);
    }

    public ArrayList<Item> addRemaining(List<Long> ids, ClientApp app) {
        ArrayList<Item> added = new ArrayList<Item>();
        for (Long id : ids) {
            Item item = new Item(id, app);
            if (!items.contains(item)) {
                items.add(item);
                added.add(item);
            }
        }
        return added;

    }

    public List<Item> getItems() {
        return items;
    }

}
