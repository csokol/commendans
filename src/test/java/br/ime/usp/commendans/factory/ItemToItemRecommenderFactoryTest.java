package br.ime.usp.commendans.factory;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import br.ime.usp.commendans.itemtoitem.ItemToItemRecommender;
import br.ime.usp.commendans.itemtoitem.ItemToItemRecommenderFactory;
import br.ime.usp.commendans.itemtoitem.ItemToItemsAssociation;
import br.ime.usp.commendans.itemtoitem.Tuple;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.model.User;

public class ItemToItemRecommenderFactoryTest {

    @Test
    public void shouldBuildRecommender() {
        ItemToItemRecommenderFactory factory = new ItemToItemRecommenderFactory();
        Item item1 = new Item(1l);
        Item item2 = new Item(2l);
        Item item3 = new Item(3l);
        Item item4 = new Item(4l);
        User user1 = new User(Arrays.asList(item1, item3), 1l);
        User user2 = new User(Arrays.asList(item2, item4), 2l);
        User user3 = new User(Arrays.asList(item1, item2, item3), 3l);
        User user4 = new User(Arrays.asList(item3, item4), 4l);
        factory.addUser(user1);
        factory.addUser(user2);
        factory.addUser(user3);
        factory.addUser(user4);
        
        ItemToItemRecommender recommender = factory.build();
        HashMap<Item, ItemToItemsAssociation> associations = factory.getAssociations();
        
        List<Tuple> recommendedItems = recommender.recommendendItemsFor(item1).getTuples();
        ItemToItemsAssociation association = associations.get(item1);
        assertEquals(0.0, association.associationValue(item1), 0.001);
        assertEquals(1.0, association.associationValue(item2), 0.001);
        assertEquals(2.0, association.associationValue(item3), 0.001);
        assertEquals(0.0, association.associationValue(item4), 0.001);
        
        assertEquals(2, recommendedItems.size());
        assertEquals(item3, recommendedItems.get(0).getItem());
        assertEquals(2.0, recommendedItems.get(0).getValue().doubleValue(), 0.001);
        assertEquals(item2, recommendedItems.get(1).getItem());
        assertEquals(1.0, recommendedItems.get(1).getValue().doubleValue(), 0.001);
        
        recommendedItems = recommender.recommendendItemsFor(item2).getTuples();
        association = associations.get(item2);
        assertEquals(1.0, association.associationValue(item1), 0.001);
        assertEquals(0.0, association.associationValue(item2), 0.001);
        assertEquals(1.0, association.associationValue(item3), 0.001);
        assertEquals(1.0, association.associationValue(item4), 0.001);
        
        assertEquals(3, recommendedItems.size());
        assertEquals(1.0, recommendedItems.get(0).getValue().doubleValue(), 0.001);
        assertEquals(1.0, recommendedItems.get(1).getValue().doubleValue(), 0.001);
        assertEquals(1.0, recommendedItems.get(2).getValue().doubleValue(), 0.001);
        
        recommendedItems = recommender.recommendendItemsFor(item3).getTuples();
        association = associations.get(item3);
        assertEquals(2.0, association.associationValue(item1), 0.001);
        assertEquals(1.0, association.associationValue(item2), 0.001);
        assertEquals(0.0, association.associationValue(item3), 0.001);
        assertEquals(1.0, association.associationValue(item4), 0.001);
        
        assertEquals(3, recommendedItems.size());
        assertEquals(item1, recommendedItems.get(0).getItem());
        assertEquals(2.0, recommendedItems.get(0).getValue().doubleValue(), 0.001);
        assertEquals(1.0, recommendedItems.get(1).getValue().doubleValue(), 0.001);
        assertEquals(1.0, recommendedItems.get(2).getValue().doubleValue(), 0.001);
        
        association = associations.get(item4);
        assertEquals(0.0, association.associationValue(item1), 0.001);
        assertEquals(1.0, association.associationValue(item2), 0.001);
        assertEquals(1.0, association.associationValue(item3), 0.001);
        assertEquals(0.0, association.associationValue(item4), 0.001);
        
    }

}
