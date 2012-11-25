package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.Application;

@Component
public class ApplicationDao {
    
    private final Session session;

    public ApplicationDao(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
    public List<Application> list() {
        return session.createCriteria(Application.class).list();
    }

    public Application findByAccessKey(String key) {
        Query query = session.createQuery("select app from Application app where app.accessKey=:key");
        query.setParameter("key", key);
        return (Application) query.uniqueResult();
    }


}
