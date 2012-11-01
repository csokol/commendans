package br.ime.usp.commendans.components;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component @RequestScoped
public class SessionCreator implements ComponentFactory<Session> {
    
    private final SessionFactory sf;
    private org.hibernate.classic.Session session;

    public SessionCreator(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Session getInstance() {
        return session;
    }
    
    @PostConstruct
    public void create() {
        session = sf.openSession();
    }
    
    @PreDestroy
    public void destroy() {
        session.close();
    }

}
