package br.ime.usp.commendans.infra;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.ime.usp.commendans.dao.ApplicationDao;
import br.ime.usp.commendans.dao.CustomerDao;
import br.ime.usp.commendans.model.Application;
import br.ime.usp.commendans.model.Customer;
import br.ime.usp.commendans.recommender.GeneralRecommender;
import br.ime.usp.commendans.recommender.itemtoitem.SingleAppRecommender;
import br.ime.usp.commendans.recommender.itemtoitem.SingleAppRecommenderFactory;

@Component @ApplicationScoped
public class RecommenderCreator implements ComponentFactory<GeneralRecommender> {
    private final CustomerDao customerDao;
    private final SingleAppRecommenderFactory factory;
    private GeneralRecommender recommender;
    private final ApplicationDao appDao;

    public RecommenderCreator(SingleAppRecommenderFactory factory, SessionFactory sf) {
        Session session = sf.openSession();
        this.customerDao = new CustomerDao(session);
        this.appDao = new ApplicationDao(session);
        this.factory = factory;
    }

    @Override
    public GeneralRecommender getInstance() {
        return recommender;
    }
    
    @PostConstruct
    public void create() {
        List<Application> apps = appDao.list();
        for (Application app : apps) {
            List<Customer> customers = customerDao.findCustomersOf(app);
            SingleAppRecommender recommender = buildSingleAppRecommender(customers);
            app.use(recommender);
        }
        this.recommender = new GeneralRecommender(apps);
    }

    private SingleAppRecommender buildSingleAppRecommender(List<Customer> users) {
        factory.clean();
        for (Customer user : users) {
            factory.addUser(user);
        }
        return factory.build();
    }
}
