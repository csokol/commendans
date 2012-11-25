package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.Application;
import br.ime.usp.commendans.model.Item;

@Component
public class ItemDao {
    
    private final Session session;

    public ItemDao(Session session) {
        this.session = session;
    }

    public Item findByAppItemId(Long appItemId, Application app) {
        Query query = session.createQuery("select item from Item item where item.appItemId = :id and item.app.id = :appId");
        query.setLong("id", appItemId);
        query.setLong("appId", app.getId());
        return (Item) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Item> findAppItemIds(List<Long> itemsIds, Application app) {
        return session
                .createQuery("select item from Item item where item.appItemId in :ids and item.app.id = :appId")
                .setParameterList("ids", itemsIds)
                .setParameter("appId", app.getId())
                .list();
    }

}
