package br.ime.usp.commendans.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ime.usp.commendans.model.ClientApp;
import br.ime.usp.commendans.model.Customer;
import br.ime.usp.commendans.model.Item;

public class CustomerDaoTest extends DaoTest {

    private static CustomerDao userDao;
    private static ClientApp app;

    @Before
    public void setUp() {
        userDao = new CustomerDao(session);
        app = new ClientApp("cdc", "123");
        session.beginTransaction();
        session.save(app);
    }
    
    @After
    public void tearDown() {
        session.getTransaction().rollback();
        session.clear();
    }

    @Test
    public void shouldFindUsers() {
        Item item = new Item(1l, app);
        session.save(item);
        List<Item> items = Arrays.asList(item);
        session.save(new Customer(items, 1l, app));
        session.save(new Customer(items, 2l, app));
        session.save(new Customer(items, 3l, app));
        List<Customer> users = userDao.list();
        assertEquals(3, users.size());
    }

    @Test
    public void shouldFindUsersOfSpecificApp() throws Exception {
        saveCustomers();    

        List<Customer> customers = userDao.findCustomersOf(app);
        assertEquals(3, customers.size());
        assertEquals("cdc", customers.get(0).getApp().getName());
        assertEquals("cdc", customers.get(1).getApp().getName());
        assertEquals("cdc", customers.get(2).getApp().getName());
    }
    
    @Test
    public void shouldFindCustomerWithIdAndApp() throws Exception {
        saveCustomers();    
        
        Customer c = userDao.find(app, 1l);
        assertEquals("cdc", c.getApp().getName());
        assertEquals(1l, c.getClientAppCustomerId().longValue());
    }

    private void saveCustomers() {
        Item item = new Item(1l, app);
        session.save(item);
        List<Item> items = Arrays.asList(item);
        session.save(new Customer(items, 1l, app));
        session.save(new Customer(items, 2l, app));
        session.save(new Customer(items, 3l, app));
        ClientApp otherApp = new ClientApp("gnarus", "123");
        item = new Item(2l, otherApp);
        items = Arrays.asList(item);
        session.save(otherApp);
        session.save(item);
        session.save(new Customer(items, 1l, otherApp));
        session.save(new Customer(items, 2l, otherApp));
        session.save(new Customer(items, 3l, otherApp));
    }

}
