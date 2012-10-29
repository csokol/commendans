package br.ime.usp.commendans.model;

import java.util.List;

public class User {

    private final List<Item> items;

    public User(List<Item> items) {
        this.items = items;
    }

    public List<Item> itemsBought() {
        return items;
    }

}
