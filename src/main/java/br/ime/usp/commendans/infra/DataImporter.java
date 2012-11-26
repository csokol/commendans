package br.ime.usp.commendans.infra;

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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.ime.usp.commendans.model.ClientApp;
import br.ime.usp.commendans.model.Customer;
import br.ime.usp.commendans.model.Item;

public class DataImporter {
    
    private final Session session;
    private static Logger logger = Logger.getLogger(DataImporter.class);

    public DataImporter(Session session) {
        this.session = session;
    }

    public static void main(String[] args) throws IOException {
        SessionFactory sf = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        DataImporter dataImporter = new DataImporter(session);
        dataImporter.importData("/orders.csv");
    }

    public void importData(String file) {
        HashMap<Long, Customer> users = new HashMap<Long, Customer>();
        HashMap<Long, Item> items = new HashMap<Long, Item>();
        InputStream resourceAsStream = getClass().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line = safeReadLine(reader);
        ClientApp cdc = new ClientApp("Casa do Código", "123");
        session.save(cdc);
        
        while (line != null) {
            logger.debug(line);
            List<String> row = Arrays.asList(line.split(","));
            long id = Long.parseLong(row.get(0));
            long itemId = Long.parseLong(row.get(1).charAt(7) + "");
            Customer user = users.get(id);
            if (user == null) {
                user = new Customer(new ArrayList<Item>(), id, cdc);
            }
            Item item = items.get(itemId);
            if (item == null) {
                item = new Item(itemId, cdc);
            }
            user.add(item);
            line = safeReadLine(reader);
            users.put(id, user);
            items.put(itemId, item);
        }
        
        logger.debug("persisting");
        session.getTransaction().begin();
        for (Item item : items.values()) {
            session.save(item);
        }
        
        Collection<Customer> allUsers = users.values();
        for (Customer user : allUsers) {
            session.save(user);
        }
        session.getTransaction().commit();
        logger.debug("finished persisting");
    }

    private String safeReadLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("could not read file", e);
        }
    }

}
