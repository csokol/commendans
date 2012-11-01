package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.User;

@Component
public class UserDao {
    private final Session session;

    public UserDao(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
    public List<User> list() {
        session.createQuery("select user from User user join fetch user.items");
        return session.createCriteria(User.class).list();
    }
}
