package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.Application;
import br.ime.usp.commendans.model.Customer;

@Component
public class CustomerDao {
    private final Session session;

    public CustomerDao(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
    public List<Customer> list() {
        Query query = session.createQuery("select customer from Customer customer join fetch customer.items");
        return session.createCriteria(Customer.class).list();
    }

    public List<Customer> findCustomersOf(Application app) {
        Query query = session.createQuery("select customer from Customer customer " +
        		"join fetch customer.items " +
        		"where customer.app.name like :appName");
        query.setParameter("appName", app.getName());
        return query.list();
    }
}
