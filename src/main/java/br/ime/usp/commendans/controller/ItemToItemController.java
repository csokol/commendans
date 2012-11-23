package br.ime.usp.commendans.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.ime.usp.commendans.dao.ItemDao;
import br.ime.usp.commendans.infra.TupleJsonSerializer;
import br.ime.usp.commendans.itemtoitem.SingleAppRecommender;
import br.ime.usp.commendans.itemtoitem.ItemVector;
import br.ime.usp.commendans.itemtoitem.Tuple;
import br.ime.usp.commendans.model.Item;

@Resource
public class ItemToItemController {
    private final SingleAppRecommender itemToItem;
    private final ItemDao itemDao;
    private final Result result;
    private final TupleJsonSerializer serializer;

    public ItemToItemController(SingleAppRecommender itemToItem, ItemDao itemDao, Result result, TupleJsonSerializer serializer) {
        this.itemToItem = itemToItem;
        this.itemDao = itemDao;
        this.result = result;
        this.serializer = serializer;
    }
    
    @Get("/recommend/item/{itemId}")
    public void recommend(Long itemId) {
        Item item = itemDao.find(itemId);
        ItemVector recommendend = itemToItem.recommendendItemsFor(item);
        serializeResult(recommendend.getTuples());
    }
    
    @Get("/recommend/items/")
    public void recommend(List<Long> itemsIds) {
        List<Item> items = itemDao.find(itemsIds);
        List<Tuple> recommendend = itemToItem.recommendendItemsFor(items).getTuples();
        serializeResult(recommendend);
    }

    private void serializeResult(List<Tuple> recommendend) {
        result.use(Results.http())
            .addHeader("content-type", "application/json")
            .body(serializer.toJson(recommendend));
    }
}
