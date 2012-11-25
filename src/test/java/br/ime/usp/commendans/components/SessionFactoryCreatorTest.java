package br.ime.usp.commendans.components;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.ServletContext;

import org.junit.Ignore;
import org.junit.Test;

import br.ime.usp.commendans.infra.SessionFactoryCreator;

public class SessionFactoryCreatorTest {

    @Test @Ignore
    public void shouldGetSessionFactory() {
        ServletContext context = mock(ServletContext.class);
        when(context.getInitParameter("environment")).thenReturn("heroku");
        SessionFactoryCreator sfc = new SessionFactoryCreator(context);
        sfc.create();
    }

}
