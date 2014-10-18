package by.shoptrip.db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * WTH.by ShopTrip.by
 * User: Aliaksei Chynayeu
 * Date: 18.10.14
 */
public abstract class AbstractDBManager implements DBManager {
    protected static String host="localhost", dbname="shoptrip";

    public void init() {
        //TODO [AC] create config file
    /*    Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("d:\\Work\\WTH\\ShopTrip\\src\\web\\db.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * method for DB connection
     * @return db - database object
     * @throws UnknownHostException
     */
    protected DBCollection getCollection(String name) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(host, 27017);
        DB db = mongoClient.getDB(dbname);
        DBCollection collection = db.getCollection(name);
        return collection;
    }

}
