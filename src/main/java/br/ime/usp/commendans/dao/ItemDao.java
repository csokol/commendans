package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.Item;

@Component
public class ItemDao {
    
    private final Session session;

    public ItemDao(Session session) {
        this.session = session;
    }

    public Item find(Long itemId) {
        return (Item) session.load(Item.class, itemId);
    }

    @SuppressWarnings("unchecked")
    public List<Item> find(List<Long> itemsIds) {
        return session
                .createQuery("select item from Item item where item.id in :ids")
                .setParameterList("ids", itemsIds)
                .list();
    }

}
