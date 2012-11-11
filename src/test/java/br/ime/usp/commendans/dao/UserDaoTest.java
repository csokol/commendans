package br.ime.usp.commendans.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void shouldFindUsers() {
        SessionFactory sf = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        CustomerDao userDao = new CustomerDao(session);
        userDao.list();
    }

}
