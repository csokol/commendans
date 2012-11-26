package br.ime.usp.commendans.recommender.itemtoitem;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import br.ime.usp.commendans.model.ClientApp;
import br.ime.usp.commendans.model.Customer;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.recommender.itemtoitem.ItemToItemsAssociation;
import br.ime.usp.commendans.recommender.itemtoitem.SingleAppRecommender;
import br.ime.usp.commendans.recommender.itemtoitem.SingleAppRecommenderFactory;
import br.ime.usp.commendans.recommender.itemtoitem.Tuple;

public class SingleAppRecommenderFactoryTest {

    @Test
    public void shouldBuildRecommenderFromCollectiveIntelligenceInActionExample() {
        SingleAppRecommenderFactory factory = new SingleAppRecommenderFactory();
        ClientApp cdc = new ClientApp("casadocodigo", "1234");
        Item item1 = new Item(1l, cdc);
        Item item2 = new Item(2l, cdc);
        Item item3 = new Item(3l, cdc);
        Item item4 = new Item(4l, cdc);
        Customer user1 = new Customer(Arrays.asList(item1, item3), 1l, cdc);
        Customer user2 = new Customer(Arrays.asList(item2, item4), 2l, cdc);
        Customer user3 = new Customer(Arrays.asList(item1, item2, item3), 3l, cdc);
        Customer user4 = new Customer(Arrays.asList(item3, item4), 4l, cdc);
        factory.addUser(user1);
        factory.addUser(user2);
        factory.addUser(user3);
        factory.addUser(user4);
        
        SingleAppRecommender recommender = factory.build();
        HashMap<Item, ItemToItemsAssociation> associations = factory.getAssociations();
        
        List<Tuple> recommendedItems = recommender.recommendedItemsFor(item1).getTuples();
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
        
        recommendedItems = recommender.recommendedItemsFor(item2).getTuples();
        association = associations.get(item2);
        assertEquals(1.0, association.associationValue(item1), 0.001);
        assertEquals(0.0, association.associationValue(item2), 0.001);
        assertEquals(1.0, association.associationValue(item3), 0.001);
        assertEquals(1.0, association.associationValue(item4), 0.001);
        
        assertEquals(3, recommendedItems.size());
        assertEquals(1.0, recommendedItems.get(0).getValue().doubleValue(), 0.001);
        assertEquals(1.0, recommendedItems.get(1).getValue().doubleValue(), 0.001);
        assertEquals(1.0, recommendedItems.get(2).getValue().doubleValue(), 0.001);
        
        recommendedItems = recommender.recommendedItemsFor(item3).getTuples();
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
