package br.ime.usp.commendans.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.ime.usp.commendans.dao.ClientAppDao;
import br.ime.usp.commendans.dao.CustomerDao;
import br.ime.usp.commendans.dao.ItemDao;
import br.ime.usp.commendans.model.ClientApp;
import br.ime.usp.commendans.model.Customer;
import br.ime.usp.commendans.model.GroupedItems;
import br.ime.usp.commendans.model.Item;

@Resource
public class ClientAppController {

    private final Result result;
    private final ClientAppDao appDao;
    private final ItemDao itemDao;
    private final CustomerDao customerDao;

    public ClientAppController(Result result, ClientAppDao appDao, ItemDao itemDao, CustomerDao customerDao) {
        this.result = result;
        this.appDao = appDao;
        this.itemDao = itemDao;
        this.customerDao = customerDao;
    }
    
    //clientAppUserId=100&itemsIds[0]=1&itemsIds[1]=2&itemsIds[2]=3
    @Post("app/{key}/addSale")
    public void addSale(String key, Long clientAppUserId, List<Long> itemsIds) {
        ClientApp app = appDao.findByAccessKey(key);
        GroupedItems groupedItems = itemDao.findItems(itemsIds, app);
        
        ArrayList<Item> newItems = groupedItems.addRemaining(itemsIds, app);
        
        for (Item item : newItems) {
            itemDao.save(item);
        }
        
        Customer customer = customerDao.find(app, clientAppUserId);
        List<Item> allItems = groupedItems.getItems();
        if (customer == null) {
            customer = new Customer(new ArrayList<Item>(), clientAppUserId, app);
        }
        for (Item item : allItems) {
            customer.add(item);
        }
        customerDao.save(customer);
        result.use(Results.json()).from(true).serialize();
    }
    
    @Get("app/new")
    public void clientAppForm() {
    }
    
    @Post("app/new")
    public void saveApp(String name) {
        String accessKey = "123" + name;
        accessKey = DigestUtils.sha256Hex(accessKey);
        appDao.save(new ClientApp(name, accessKey));
        result.include("key", accessKey);
    }

}
