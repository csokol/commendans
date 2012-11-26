package br.ime.usp.commendans.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.ime.usp.commendans.dao.ClientAppDao;
import br.ime.usp.commendans.dao.ItemDao;
import br.ime.usp.commendans.infra.TupleJsonSerializer;
import br.ime.usp.commendans.model.ClientApp;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.recommender.GeneralRecommender;
import br.ime.usp.commendans.recommender.itemtoitem.ItemVector;
import br.ime.usp.commendans.recommender.itemtoitem.Tuple;

@Resource
public class ItemToItemController {
    private final ItemDao itemDao;
    private final Result result;
    private final TupleJsonSerializer serializer;
    private final GeneralRecommender recommender;
    private final ClientAppDao appDao;

    public ItemToItemController(GeneralRecommender recommender, 
            ItemDao itemDao, ClientAppDao appDao, Result result, 
            TupleJsonSerializer serializer) {
        this.recommender = recommender;
        this.itemDao = itemDao;
        this.appDao = appDao;
        this.result = result;
        this.serializer = serializer;
    }
    
    @Get("/recommend/item/{appItemId}")
    public void recommend(Long appItemId, String accessKey) {
        if (validKey(accessKey)) {
            ClientApp app = appDao.findByAccessKey(accessKey);
            Item item = itemDao.findByAppItemId(appItemId, app);
            ItemVector recommended = recommender.recommendedItemsFor(item, accessKey);
            serializeResult(recommended);
        }
    }
    

    @Get("/recommend/items/")
    public void recommend(List<Long> itemsIds, String accessKey) {
        if (validKey(accessKey)) {
            ClientApp app = appDao.findByAccessKey(accessKey);
            List<Item> items = itemDao.findItems(itemsIds, app).getItems();
            ItemVector recommended = recommender.recommendedItemsFor(items, accessKey);
            serializeResult(recommended);
        }
    }

    private boolean validKey(String accessKey) {
        ClientApp app = appDao.findByAccessKey(accessKey);
        if (app == null) {
            result.notFound();
            return false;
        }
        return true;
    }

    private void serializeResult(ItemVector recommended) {
        List<Tuple> tuples = recommended == null ? new ArrayList<Tuple>() : recommended.getTuples();
        result.use(Results.http())
            .addHeader("content-type", "application/json")
            .body(serializer.toJson(tuples));
    }
}
