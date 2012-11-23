package br.ime.usp.commendans.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ime.usp.commendans.model.Application;
import br.ime.usp.commendans.model.Item;

public class ItemDaoTest extends DaoTest {
    
    private ItemDao itemDao;

    @Before
    public void setUp() {
        itemDao = new ItemDao(session);
        session.beginTransaction();
    }
    
    @After
    public void tearDown() {
        session.getTransaction().rollback();
        session.clear();
    }
    
    @Test
    public void shouldFindByAppItemId() throws Exception {
        Application app = new Application("cdc", "123");
        session.save(app);
        session.save(new Item(11l, app));
        Item item = itemDao.findByAppItemId(11l);
        assertEquals(11l, item.getAppItemId().longValue());
    }

}
