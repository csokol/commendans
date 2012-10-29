package br.ime.usp.commendans.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;

import br.ime.usp.commendans.model.Item;
import br.ime.usp.commendans.model.User;

public class DataImporter {
    
    private final StatelessSession session;
    private static Logger logger;

    public DataImporter(StatelessSession session) {
        this.session = session;
    }

    public static void main(String[] args) throws IOException {
        logger = Logger.getLogger(DataImporter.class);
        SessionFactory sf = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        StatelessSession session = sf.openStatelessSession();
        DataImporter dataImporter = new DataImporter(session);
        dataImporter.importData("/orders.csv");
    }

    private void importData(String file) throws IOException {
        HashMap<Long, User> users = new HashMap<Long, User>();
        HashMap<Long, Item> items = new HashMap<Long, Item>();
        InputStream resourceAsStream = getClass().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line = reader.readLine();
        
        while (line != null) {
            logger.info(line);
            List<String> row = Arrays.asList(line.split(","));
            long id = Long.parseLong(row.get(0));
            long itemId = Long.parseLong(row.get(1).charAt(7) + "");
            User user = users.get(id);
            if (user == null) {
                user = new User(new ArrayList<Item>(), id);
            }
            Item item = items.get(itemId);
            if (item == null) {
                item = new Item(itemId);
            }
            user.add(item);
            line = reader.readLine();
            users.put(id, user);
            items.put(itemId, item);
        }
        
        logger.info("persisting");
        session.getTransaction().begin();
        for (Item item : items.values()) {
            session.insert(item);
        }
        
        Collection<User> allUsers = users.values();
        for (User user : allUsers) {
            session.insert(user);
        }
        session.getTransaction().commit();
        logger.info("finished persisting");
    }

}
