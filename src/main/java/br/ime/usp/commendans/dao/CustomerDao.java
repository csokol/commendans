package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.PrototypeScoped;
import br.ime.usp.commendans.model.ClientApp;
import br.ime.usp.commendans.model.Customer;

@Component @PrototypeScoped
public class CustomerDao {
    private final Session session;

    public CustomerDao(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
    public List<Customer> list() {
        return session.createCriteria(Customer.class).list();
    }

    @SuppressWarnings("unchecked")
    public List<Customer> findCustomersOf(ClientApp app) {
        Query query = session.createQuery("select customer from Customer customer " +
        		"where customer.app.name like :appName");
        query.setParameter("appName", app.getName());
        return query.list();
    }

    public void save(Customer customer) {
        session.save(customer);
    }

    public Customer find(ClientApp app, Long customerId) {
        Query query = session.createQuery("select customer from Customer customer " +
                "where customer.app.id = :appId " +
                "and customer.clientAppCustomerId = :customerId");
        query.setParameter("appId", app.getId());
        query.setParameter("customerId", customerId);
        return (Customer) query.uniqueResult();
    }
}
