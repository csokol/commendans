package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import br.ime.usp.commendans.model.User;

public class UserDaoTest {

    @Test
    public void test() {
        SessionFactory sf = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        UserDao userDao = new UserDao(session);
        List<User> users = userDao.list();
    }

}
