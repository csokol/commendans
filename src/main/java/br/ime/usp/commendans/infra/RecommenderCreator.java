package br.ime.usp.commendans.infra;

import java.util.List;

import javax.annotation.PostConstruct;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.ime.usp.commendans.dao.CustomerDao;
import br.ime.usp.commendans.itemtoitem.ItemToItemRecommenderFactory;
import br.ime.usp.commendans.itemtoitem.SingleAppRecommender;
import br.ime.usp.commendans.model.Customer;
import br.ime.usp.commendans.recommender.GeneralRecommender;

@Component
public class RecommenderCreator implements ComponentFactory<GeneralRecommender> {
    private final CustomerDao dao;
    private final ItemToItemRecommenderFactory factory;
    private SingleAppRecommender itemToItemRecommender;
    private GeneralRecommender recommender;

    public RecommenderCreator(CustomerDao dao, ItemToItemRecommenderFactory factory) {
        this.dao = dao;
        this.factory = factory;
    }

    @Override
    public GeneralRecommender getInstance() {
        return recommender;
    }
    
    @PostConstruct
    public void create() {
        List<Customer> users = dao.list();
        this.itemToItemRecommender = buildSingleAppRecommender(users);
    }

    private SingleAppRecommender buildSingleAppRecommender(List<Customer> users) {
        for (Customer user : users) {
            factory.addUser(user);
        }
        return factory.build();
    }
}
