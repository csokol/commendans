package br.ime.usp.commendans.recommender;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.ime.usp.commendans.dao.ClientAppDao;
import br.ime.usp.commendans.dao.CustomerDao;
import br.ime.usp.commendans.model.ClientApp;
import br.ime.usp.commendans.model.Customer;
import br.ime.usp.commendans.recommender.itemtoitem.SingleAppRecommender;
import br.ime.usp.commendans.recommender.itemtoitem.SingleAppRecommenderFactory;

@Component @ApplicationScoped
public class RecommenderCreator implements ComponentFactory<GeneralRecommender> {
    private final CustomerDao customerDao;
    private final SingleAppRecommenderFactory factory;
    private GeneralRecommender recommender;
    private final ClientAppDao appDao;

    public RecommenderCreator(SingleAppRecommenderFactory factory, SessionFactory sf) {
        Session session = sf.openSession();
        this.customerDao = new CustomerDao(session);
        this.appDao = new ClientAppDao(session);
        this.factory = factory;
    }

    @Override
    public GeneralRecommender getInstance() {
        return recommender;
    }
    
    @PostConstruct
    public void create() {
        List<ClientApp> apps = appDao.list();
        for (ClientApp app : apps) {
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
