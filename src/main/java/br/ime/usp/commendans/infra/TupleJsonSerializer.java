package br.ime.usp.commendans.infra;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.itemtoitem.Tuple;

import com.google.gson.Gson;

@Component
public class TupleJsonSerializer {
    
    private final Gson gson;

    public TupleJsonSerializer() {
        this.gson = new Gson();
    }

    public String toJson(Tuple tuple) {
        return gson.toJson(tuple);
    }
    
    public String toJson(List<Tuple> tuples) {
        return gson.toJson(tuples);
    }

    
}
