package br.ime.usp.commendans.recommender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ime.usp.commendans.model.ClientApp;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.recommender.itemtoitem.ItemVector;

public class GeneralRecommender {

    private Map<String, ClientApp> appsMap;

    public GeneralRecommender(List<ClientApp> apps) {
        appsMap = new HashMap<String, ClientApp>();
        for (ClientApp app : apps) {
            appsMap.put(app.getAccessKey(), app);
        }
    }

    public ItemVector recommendedItemsFor(Item item, String accessKey) {
        ClientApp application = appsMap.get(accessKey);
        return application.recommendedItemsFor(item);
    }

    public ItemVector recommendedItemsFor(List<Item> items, String accessKey) {
        ClientApp application = appsMap.get(accessKey);
        return application.recommendedItemsFor(items);
    }

}
