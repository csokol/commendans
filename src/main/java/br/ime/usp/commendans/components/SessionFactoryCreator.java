package br.ime.usp.commendans.components;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component @ApplicationScoped
public class SessionFactoryCreator implements ComponentFactory<SessionFactory> {

    private SessionFactory sessionFactory;
    private static Logger LOG = Logger.getLogger(SessionFactoryCreator.class);
    
    public SessionFactoryCreator() {
    }

    @Override
    public SessionFactory getInstance() {
        return sessionFactory;
    }
    
    @PreDestroy
    public void destroy() {
        sessionFactory.close();
    }
    
    @PostConstruct
    public void create() {
        //if (env.getName().equals("heroku")) {
        if (true) {
            LOG.info("using heroku specific confs");
            Configuration configuration = new Configuration().configure("/hibernate-heroku.cfg.xml");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/commendans");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "");
            
            sessionFactory = configuration.buildSessionFactory();
        } else {
            LOG.info("using heroku specific normal confs");
            sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        }
    }

}
