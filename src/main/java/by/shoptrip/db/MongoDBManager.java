package by.shoptrip.db;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Aliaksei Chynayeu
 * Date: 18.10.14
 */
public class MongoDBManager extends AbstractDBManager {

    private static String LIST_COLLECTION = "lists";

    public MongoDBManager(){
        init();
    }

    public void init(){
        super.init();
    }


    @Override
    public List<JSONObject> getLists() {

          List<JSONObject> listOfObjects = new ArrayList<JSONObject>();
        try {
            DBCollection lists = getCollection(LIST_COLLECTION);
            DBCursor curs = lists.find();
            while(curs.hasNext()) {
                DBObject o = curs.next();
                JSONObject obj = new JSONObject(String.format("%s", o).toString());

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfObjects;
    }

    @Override
    public JSONObject delete(JSONObject object) {
        DB db = null;
        JSONObject result = new JSONObject();
        try {
            DBCollection lists = db.getCollection("lists");
            DBObject objectToRemove = (DBObject) JSON.parse(object.toString());
            WriteResult writeResult = lists.remove(objectToRemove);
            if (writeResult.getN() == 0) {
              result.put("error","unable to delete");
            }
            result.put("action", "delete");
            result.put("object", object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject add(JSONObject object) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JSONObject toggle(JSONObject object) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
