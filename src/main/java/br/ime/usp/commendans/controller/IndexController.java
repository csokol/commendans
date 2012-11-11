package br.ime.usp.commendans.controller;

import org.hibernate.Session;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;


@Resource
public class IndexController {
    
    private final Result result;
    private final Session session;

    public IndexController(Result result, Session session) {
        this.result = result;
        this.session = session;
    }
    
    @Get("/")
    public void index() {
        result.use(Results.http()).body("<html><body><h1>hello world</h1</body></html>");
    }
    
    @Get("/import/askjdhakjshdakjshd")
    public void importData() {
        //DataImporter dataImporter = new DataImporter(session);
        //dataImporter.importData("/orders.csv");
        result.use(Results.http()).body("<html><body>finished persisting</body></html>");
    }
}
