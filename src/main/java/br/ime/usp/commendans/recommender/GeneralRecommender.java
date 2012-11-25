package br.ime.usp.commendans.recommender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ime.usp.commendans.model.Application;
import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.recommender.itemtoitem.ItemVector;

public class GeneralRecommender {

    private Map<String, Application> appsMap;

    public GeneralRecommender(List<Application> apps) {
        appsMap = new HashMap<String, Application>();
        for (Application app : apps) {
            appsMap.put(app.getAccessKey(), app);
        }
    }

    public ItemVector recommendedItemsFor(Item item, String accessKey) {
        Application application = appsMap.get(accessKey);
        return application.recommendedItemsFor(item);
    }

    public ItemVector recommendedItemsFor(List<Item> items, String accessKey) {
        Application application = appsMap.get(accessKey);
        return application.recommendedItemsFor(items);
    }

}
