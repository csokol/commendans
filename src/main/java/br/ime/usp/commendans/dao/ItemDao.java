package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.Item;

@Component
public class ItemDao {
    
    private final Session session;

    public ItemDao(Session session) {
        this.session = session;
    }

    public Item findByAppItemId(Long appItemId) {
        Query query = session.createQuery("select item from Item item where item.appItemId = :id");
        query.setLong("id", appItemId);
        return (Item) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Item> findAppItemIds(List<Long> itemsIds) {
        return session
                .createQuery("select item from Item item where item.appItemId in :ids")
                .setParameterList("ids", itemsIds)
                .list();
    }

}
