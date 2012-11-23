package br.ime.usp.commendans.infra;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.itemtoitem.Tuple;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class TupleJsonSerializer {
    
    private final Gson gson;

    public TupleJsonSerializer() {
        ExclusionStrategy strategy = new IncludeAllowedAttributes();
        this.gson = new GsonBuilder().addSerializationExclusionStrategy(strategy).create();
    }

    public String toJson(Tuple tuple) {
        return gson.toJson(tuple);
    }
    
    public String toJson(List<Tuple> tuples) {
        return gson.toJson(tuples);
    }
    
    private class IncludeAllowedAttributes implements ExclusionStrategy {

        private List<String> allowedAttributes = Arrays.asList("item", "appItemId", "value");

        @Override
        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes attr) {
            String name = attr.getName();
            boolean allowedAttribute = allowedAttributes.contains(name); 
            return !allowedAttribute;
        }
        
    }

    
}
