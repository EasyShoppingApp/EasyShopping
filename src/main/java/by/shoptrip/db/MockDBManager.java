package by.shoptrip.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * WTH.by ShopTrip.by
 * User: Aliaksei Chynayeu
 * Date: 18.10.14
 */
public class MockDBManager implements DBManager {
    private List<JSONObject> lists;

    public MockDBManager (){
        lists = new ArrayList<JSONObject>();
        lists.add(getMockObject());
    }
    @Override
    public JSONObject delete(JSONObject object) {
        JSONObject deleted = new JSONObject();
        try {
            if (lists.size() <= 1) {

            }
            deleted.put("action", "delete");
            deleted.put("object", object);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return deleted;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JSONObject add(JSONObject object) {
        JSONObject added = new JSONObject();
        try {
            lists.add(added);
            added.put("action", "add");
            added.put("object", object);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return added;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JSONObject toggle(JSONObject object) {
        JSONObject toggled = new JSONObject();
        try {
            toggled.put("action", "add");
            toggled.put("object", object);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return toggled;
    }

    @Override
    public List<JSONObject> getLists() {
        return lists;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private JSONObject getMockObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("shopperName", "IVAN");
            object.put("shopperEmail", "ivanov@example.com");
            JSONArray contents = new JSONArray();
            for (int i=0; i<10; i++ ) {
                JSONObject content = new JSONObject();
                content.put("productID", i);
                content.put("productName", "name " + i);
                content.put("quantity", 2);
                content.put("x", 10);
                content.put("y", 10);
                content.put("order", i);
                content.put("done", false);
                contents.put(content);
            }

            object.put("contents", contents);
            object.put("orderCompleted", true);

        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return object;
    }
}
