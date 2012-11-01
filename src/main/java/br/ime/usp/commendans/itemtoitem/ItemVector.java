package br.ime.usp.commendans.itemtoitem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemVector {

    private Set<Tuple> tuples;

    public ItemVector(Set<Tuple> tuples) {
        this.tuples = tuples;
    }

    public ItemVector(List<Tuple> tuples) {
        this(new HashSet<Tuple>(tuples));
    }

    public List<Tuple> getTuples() {
        ArrayList<Tuple> tuples = new ArrayList<Tuple>(this.tuples);
        Collections.sort(tuples);
        Collections.reverse(tuples);
        return Collections.unmodifiableList(tuples);
    }
    
    private void remove(Tuple t) {
        tuples.remove(t);
    }

    public ItemVector merge(ItemVector other) {
        Set<Tuple> tuples = new HashSet<Tuple>();
        Set<Tuple> tuplesToRemove = new HashSet<Tuple>();
        for (Tuple tuple : this.tuples) {
            for (Tuple t : other.getTuples()) {
                if (t.getItem().equals(tuple.getItem())) {
                    Tuple newTuple = new Tuple(t.getItem(), tuple.getValue() + t.getValue());
                    tuples.add(newTuple);
                    tuplesToRemove.add(t);
                    tuplesToRemove.add(tuple);
                    break;
                }
            }
        }
        tuples.addAll(this.tuples);
        tuples.addAll(other.getTuples());
        tuples.removeAll(tuplesToRemove);
        return new ItemVector(tuples);
    }

}
