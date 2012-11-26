package br.ime.usp.commendans.infra;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.ime.usp.commendans.dao.ClientAppDao;
import br.ime.usp.commendans.model.ClientApp;

@Intercepts
public class ValidAccessKeyInterceptor implements Interceptor {
    
    private final ClientAppDao appDao;
    private final Result result;
    private final HttpServletRequest request;
    private String paramName;

    public ValidAccessKeyInterceptor(HttpServletRequest request, ClientAppDao appDao, Result result) {
        this.request = request;
        this.appDao = appDao;
        this.result = result;
    }

    @Override
    public boolean accepts(ResourceMethod method) {
        Annotation[] annotations = method.getMethod().getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(ValidAccessKey.class)) {
                ValidAccessKey k = (ValidAccessKey) annotation;
                paramName = k.paramName();
            }
        }
        return method.containsAnnotation(ValidAccessKey.class);
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method,
            Object arg) throws InterceptionException {
        String accessKey = request.getParameter(paramName);
        ClientApp app = appDao.findByAccessKey(accessKey);
        if (app == null) {
            result.notFound();
        } else {
            stack.next(method, arg);
        }
    }

}
