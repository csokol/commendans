package br.ime.usp.commendans.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.ime.usp.commendans.dao.ItemDao;
import br.ime.usp.commendans.infra.TupleJsonSerializer;
import br.ime.usp.commendans.itemtoitem.ItemVector;
import br.ime.usp.commendans.itemtoitem.Tuple;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.recommender.GeneralRecommender;

@Resource
public class ItemToItemController {
    private final ItemDao itemDao;
    private final Result result;
    private final TupleJsonSerializer serializer;
    private final GeneralRecommender recommender;

    public ItemToItemController(GeneralRecommender recommender, 
            ItemDao itemDao, Result result, 
            TupleJsonSerializer serializer) {
        this.recommender = recommender;
        this.itemDao = itemDao;
        this.result = result;
        this.serializer = serializer;
    }
    
    @Get("/recommend/item/{itemId}")
    public void recommend(Long itemId, String accessKey) {
        Item item = itemDao.find(itemId);
        ItemVector recommended = recommender.recommendedItemsFor(item, accessKey);
        serializeResult(recommended.getTuples());
    }
    
    @Get("/recommend/items/")
    public void recommend(List<Long> itemsIds, String accessKey) {
        List<Item> items = itemDao.find(itemsIds);
        ItemVector recommended = recommender.recommendedItemsFor(items, accessKey);
        serializeResult(recommended.getTuples());
    }

    private void serializeResult(List<Tuple> recommendend) {
        result.use(Results.http())
            .addHeader("content-type", "application/json")
            .body(serializer.toJson(recommendend));
    }
}
