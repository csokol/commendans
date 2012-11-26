package br.ime.usp.commendans.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.ime.usp.commendans.infra.DataImporter;
import br.ime.usp.commendans.recommender.RecommenderCreator;


@Resource
public class IndexController {
    
    private final Result result;
    private final Session session;
    private final RecommenderCreator recommenderCreator;

    public IndexController(Result result, Session session, RecommenderCreator recommenderCreator) {
        this.result = result;
        this.session = session;
        this.recommenderCreator = recommenderCreator;
    }
    
    @Get("/helloworld")
    public void index() {
        result.use(Results.http()).body("<html><body><h1>hello world</h1</body></html>");
    }
    
    @Get("/import/askjdhakjshdakjshd")
    public void importData() {
        DataImporter dataImporter = new DataImporter(session);
        dataImporter.importData("/orders.csv");
        recommenderCreator.create();
        String name = "Casa do Código";
        String accessKey = "123" + name;
        accessKey = DigestUtils.sha256Hex(accessKey);
        result.use(Results.http()).body("<html><body>finished persisting, key: "+accessKey+"</body></html>");
    }
}
