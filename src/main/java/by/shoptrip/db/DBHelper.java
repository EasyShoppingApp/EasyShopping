package by.shoptrip.db;

import java.io.IOException;

/**
 * WTH.by ShopTrip.by
 * User: Aliaksei Chynayeu
 * Date: 18.10.14
 */
public class DBHelper {
    public static DBManager getManager() {
        //return new MongoDBManager();
        return new MockDBManager();
    }
}
