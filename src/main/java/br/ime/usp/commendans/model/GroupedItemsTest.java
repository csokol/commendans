package br.ime.usp.commendans.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class GroupedItemsTest {

    @Test
    public void shouldAddReaminingItems() {
        Item item1 = new Item(1l, null);
        Item item2 = new Item(2l, null);
        GroupedItems groupedItems = new GroupedItems(Arrays.asList(item1, item2));
        List<Long> ids = Arrays.asList(1l, 2l, 3l, 4l);
        groupedItems.addRemaining(ids, null);
        
        List<Item> items = groupedItems.getItems();
        
        assertEquals(4, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
        assertTrue(items.contains(new Item(3l, null)));
        assertTrue(items.contains(new Item(4l, null)));
    }

}
