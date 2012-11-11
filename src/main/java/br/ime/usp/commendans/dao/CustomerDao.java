package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.Customer;

@Component
public class CustomerDao {
    private final Session session;

    public CustomerDao(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
    public List<Customer> list() {
        session.createQuery("select customer from Customer customer join fetch customer.items");
        return session.createCriteria(Customer.class).list();
    }
}
