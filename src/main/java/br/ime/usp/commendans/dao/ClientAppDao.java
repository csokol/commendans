package br.ime.usp.commendans.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.ime.usp.commendans.model.ClientApp;

@Component
public class ClientAppDao {
    
    private final Session session;

    public ClientAppDao(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
    public List<ClientApp> list() {
        return session.createCriteria(ClientApp.class).list();
    }

    public ClientApp findByAccessKey(String key) {
        Query query = session.createQuery("select app from ClientApp app where app.accessKey=:key");
        query.setParameter("key", key);
        return (ClientApp) query.uniqueResult();
    }


}
