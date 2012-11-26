package br.ime.usp.commendans.recommender.itemtoitem;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import br.ime.usp.commendans.model.ClientApp;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.recommender.itemtoitem.ItemVector;
import br.ime.usp.commendans.recommender.itemtoitem.Tuple;

public class ItemVectorTest {

    @Test
    public void shouldMergeTuples() {
        ClientApp cdc = new ClientApp("Casa do Código", "123");
        Item item1 = new Item(1l, cdc);
        Item item2 = new Item(2l, cdc);
        Item item3 = new Item(3l, cdc);
        Item item4 = new Item(4l, cdc);
        List<Tuple> tuples = asList(new Tuple(item1, 11.0), new Tuple(item2, 8.0), new Tuple(item3, 2.0));
        ItemVector itemVector1 = new ItemVector(new HashSet<Tuple>(tuples));
        tuples = asList(new Tuple(item4, 10.0), new Tuple(item3, 7.0));
        ItemVector itemVector2 = new ItemVector(new HashSet<Tuple>(tuples));
        
        ItemVector merge = itemVector1.merge(itemVector2);
        List<Tuple> expected = asList(new Tuple(item1, 11.0), new Tuple(item4, 10.0), new Tuple(item3, 9.0), new Tuple(item2, 8.0));
        assertEquals(expected, merge.getTuples());
        
    }

    private List<Tuple> asList(Tuple ... tuples) {
        ArrayList<Tuple> tuplesList = new ArrayList<Tuple>();
        for (int i = 0; i < tuples.length; i++) {
            tuplesList.add(tuples[i]);
        }
        return tuplesList;
    }

}
