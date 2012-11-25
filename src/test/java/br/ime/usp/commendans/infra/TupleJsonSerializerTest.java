package br.ime.usp.commendans.infra;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import br.ime.usp.commendans.model.Application;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.recommender.itemtoitem.Tuple;

public class TupleJsonSerializerTest {

    @Test
    public void shouldSerializeATuple() {
        TupleJsonSerializer serializer = new TupleJsonSerializer();
        Application cdc = new Application("Casa do Código", "123");
        String json = serializer.toJson(new Tuple(new Item(1l, cdc), 10.0));
        assertEquals("{\"item\":{\"appItemId\":1},\"value\":10.0}", json);
    }
    
    @Test
    public void shouldSerializeATupleList() {
        TupleJsonSerializer serializer = new TupleJsonSerializer();
        Application cdc = new Application("Casa do Código", "123");
        String json = serializer.toJson(Arrays.asList(new Tuple(new Item(1l, cdc), 10.0)));
        assertEquals("[{\"item\":{\"appItemId\":1},\"value\":10.0}]", json);
    }

}
