package br.ime.usp.commendans.infra;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import br.ime.usp.commendans.itemtoitem.Tuple;
import br.ime.usp.commendans.model.Item;

public class TupleJsonSerializerTest {

    @Test
    public void shouldSerializeATuple() {
        TupleJsonSerializer serializer = new TupleJsonSerializer();
        String json = serializer.toJson(new Tuple(new Item(1l), 10.0));
        assertEquals("{\"item\":{\"id\":1},\"value\":10.0}", json);
    }
    
    @Test
    public void shouldSerializeATupleList() {
        TupleJsonSerializer serializer = new TupleJsonSerializer();
        String json = serializer.toJson(Arrays.asList(new Tuple(new Item(1l), 10.0)));
        assertEquals("[{\"item\":{\"id\":1},\"value\":10.0}]", json);
    }

}
