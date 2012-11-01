package br.ime.usp.commendans.infra;

import java.util.List;

import javax.annotation.PostConstruct;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.ime.usp.commendans.dao.UserDao;
import br.ime.usp.commendans.itemtoitem.ItemToItemRecommender;
import br.ime.usp.commendans.itemtoitem.ItemToItemRecommenderFactory;
import br.ime.usp.commendans.model.User;

@Component
public class ItemToItemRecommenderCreator implements ComponentFactory<ItemToItemRecommender> {
    private final UserDao dao;
    private final ItemToItemRecommenderFactory factory;
    private ItemToItemRecommender itemToItemRecommender;

    public ItemToItemRecommenderCreator(UserDao dao, ItemToItemRecommenderFactory factory) {
        this.dao = dao;
        this.factory = factory;
    }

    @Override
    public ItemToItemRecommender getInstance() {
        return itemToItemRecommender;
    }
    
    @PostConstruct
    public void create() {
        List<User> users = dao.list();
        for (User user : users) {
            factory.addUser(user);
        }
        this.itemToItemRecommender = factory.build();
    }
}
