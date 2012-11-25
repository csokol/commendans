package br.ime.usp.commendans.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ime.usp.commendans.model.Application;

public class ApplicationDaoTest extends DaoTest {
    private static ApplicationDao applicationDao;

    @Before
    public void setUp() {
        applicationDao = new ApplicationDao(session);
        session.beginTransaction();
    }
    
    @After
    public void tearDown() {
        session.getTransaction().rollback();
        session.clear();
    }
    
    @Test
    public void shouldListApps() throws Exception {
        session.save(new Application("cdc", "123"));
        session.save(new Application("caelum", "1234"));
        List<Application> apps = applicationDao.list();
        assertEquals(2, apps.size());
    }
    
    @Test
    public void shouldFindAppByAccessKey() throws Exception {
        session.save(new Application("cdc", "123"));
        session.save(new Application("caelum", "1234"));
        Application app = applicationDao.findByAccessKey("1234");
        assertEquals("caelum", app.getName());
    }

}
