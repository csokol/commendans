package br.ime.usp.commendans.recommender.itemtoitem;

import br.ime.usp.commendans.model.Item;

public class Tuple implements Comparable<Tuple> {
    private Item item;
    private Double value;

    public Tuple(Item item, Double value) {
        this.item = item;
        this.value = value;
    }

    public Item getItem() {
        return item;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public int compareTo(Tuple o) {
        return (int) (value - o.getValue());
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tuple other = (Tuple) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[Tuple: item=" + item + "]";
    }

}
