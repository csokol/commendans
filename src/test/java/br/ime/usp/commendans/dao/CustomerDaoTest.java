package br.ime.usp.commendans.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ime.usp.commendans.model.Application;
import br.ime.usp.commendans.model.Customer;
import br.ime.usp.commendans.model.Item;

public class CustomerDaoTest {

    private static Session session;
    private static CustomerDao userDao;
    private static Application app;

    @BeforeClass
    public static void setUpClass() {
        SessionFactory sf = new Configuration().configure(
                "/hibernate.test.cfg.xml").buildSessionFactory();
        session = sf.openSession();
        userDao = new CustomerDao(session);
        app = new Application("cdc", "123");
        session.save(app);
    }
    
    @Before
    public void setUp() {
        session.beginTransaction();
    }
    
    @After
    public void tearDown() {
        session.getTransaction().rollback();
    }

    @Test
    public void shouldFindUsers() {
        List<Item> items = Arrays.asList();
        session.save(new Customer(items, 1l, app));
        session.save(new Customer(items, 2l, app));
        session.save(new Customer(items, 3l, app));
        List<Customer> users = userDao.list();
        assertEquals(3, users.size());
    }

    @Test
    public void shouldFindUsersOfSpecificApp() throws Exception {
        Item item = new Item(1l, app);
        List<Item> items = Arrays.asList(item);
        session.save(new Customer(items, 1l, app));
        session.save(new Customer(items, 2l, app));
        session.save(new Customer(items, 3l, app));
        Application otherApp = new Application("gnarus", "123");
        item = new Item(2l, otherApp);
        items = Arrays.asList(item);
        session.save(otherApp);
        session.save(item);
        session.save(new Customer(items, 1l, otherApp));
        session.save(new Customer(items, 2l, otherApp));
        session.save(new Customer(items, 3l, otherApp));

        List<Customer> customers = userDao.findCustomersOf(app);
        assertEquals(3, customers.size());
        assertEquals("cdc", customers.get(0).getApp().getName());
        assertEquals("cdc", customers.get(1).getApp().getName());
        assertEquals("cdc", customers.get(2).getApp().getName());

    }

}
