package by.shoptrip.db;

import org.json.JSONObject;

import java.util.List;

/**
 * WTH.by ShopTrip.by
 * User: Aliaksei Chynayeu
 * Date: 18.10.14
 */
public interface DBManager {
    public JSONObject delete (JSONObject object);
    public JSONObject add (JSONObject object);
    public JSONObject toggle (JSONObject object);
    public List<JSONObject> getLists ();

}
