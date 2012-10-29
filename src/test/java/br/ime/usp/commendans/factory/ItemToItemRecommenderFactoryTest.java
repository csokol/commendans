package br.ime.usp.commendans.factory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ime.usp.commendans.factory.ItemToItemRecommenderFactory;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.model.User;

public class ItemToItemRecommenderFactoryTest {

    @Test
    public void test() {
        ItemToItemRecommenderFactory factory = new ItemToItemRecommenderFactory();
        List<Item> items = new ArrayList<Item>();
        factory.addUser(new User(items));
    }

}
