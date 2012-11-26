package br.ime.usp.commendans.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ime.usp.commendans.model.ClientApp;

public class ClientAppDaoTest extends DaoTest {
    private static ClientAppDao applicationDao;

    @Before
    public void setUp() {
        applicationDao = new ClientAppDao(session);
        session.beginTransaction();
    }
    
    @After
    public void tearDown() {
        session.getTransaction().rollback();
        session.clear();
    }
    
    @Test
    public void shouldListApps() throws Exception {
        session.save(new ClientApp("cdc", "123"));
        session.save(new ClientApp("caelum", "1234"));
        List<ClientApp> apps = applicationDao.list();
        assertEquals(2, apps.size());
    }
    
    @Test
    public void shouldFindAppByAccessKey() throws Exception {
        session.save(new ClientApp("cdc", "123"));
        session.save(new ClientApp("caelum", "1234"));
        ClientApp app = applicationDao.findByAccessKey("1234");
        assertEquals("caelum", app.getName());
    }

}
