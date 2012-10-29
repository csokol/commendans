package br.ime.usp.recommender;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;


@Resource
public class IndexController {
    
    private final Result result;

    public IndexController(Result result) {
        this.result = result;
    }
    
    @Get("/")
    public void index() {
        result.use(Results.http()).body("<html><body><h1>hello world</h1</body></html>");
    }
}
