package br.ime.usp.commendans.infra;

import java.util.List;

import javax.annotation.PostConstruct;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.ime.usp.commendans.dao.CustomerDao;
import br.ime.usp.commendans.itemtoitem.ItemToItemRecommender;
import br.ime.usp.commendans.itemtoitem.ItemToItemRecommenderFactory;
import br.ime.usp.commendans.model.Customer;

@Component @ApplicationScoped
public class ItemToItemRecommenderCreator implements ComponentFactory<ItemToItemRecommender> {
    private final CustomerDao dao;
    private final ItemToItemRecommenderFactory factory;
    private ItemToItemRecommender itemToItemRecommender;

    public ItemToItemRecommenderCreator(CustomerDao dao, ItemToItemRecommenderFactory factory) {
        this.dao = dao;
        this.factory = factory;
    }

    @Override
    public ItemToItemRecommender getInstance() {
        return itemToItemRecommender;
    }
    
    @PostConstruct
    public void create() {
        List<Customer> users = dao.list();
        for (Customer user : users) {
            factory.addUser(user);
        }
        this.itemToItemRecommender = factory.build();
    }
}
