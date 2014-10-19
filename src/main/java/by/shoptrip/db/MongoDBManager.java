package by.shoptrip.db;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: Aliaksei Chynayeu
 * Date: 18.10.14
 */
public class MongoDBManager extends AbstractDBManager {

    private static String CONTENTS_COLLECTION = "contents";

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
            DBCollection lists = getCollection(CONTENTS_COLLECTION);
            DBCursor curs = lists.find();
            while(curs.hasNext()) {
                DBObject o = curs.next();
                JSONObject obj = new JSONObject(String.format("%s", o).toString());
                listOfObjects.add(obj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfObjects;
    }

    @Override
    public JSONObject delete(JSONObject object) {
        try {
            DBCollection lists = getCollection(CONTENTS_COLLECTION);
            DBObject objectToRemove = (DBObject) JSON.parse(object.toString());

            WriteResult writeResult = lists.remove(objectToRemove);
            if (writeResult.getN() == 0) {
             // return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return object;
    }

    @Override
    public JSONObject add(JSONObject object) {
        JSONObject obj = object;
        try {
            //add random x and y
            generateRandomXY(object);
            DBCollection lists = getCollection(CONTENTS_COLLECTION);
            DBObject objectToAdd = (DBObject) JSON.parse(object.toString());
            WriteResult writeResult = lists.insert(objectToAdd);
            obj = new JSONObject(String.format("%s", objectToAdd).toString());
            if (writeResult.getN() == 0) {
               // return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }

    @Override
    public JSONObject toggle(JSONObject object) {
           try {
            DBCollection lists = getCollection(CONTENTS_COLLECTION);
            DBObject objectToUpdate = (DBObject) JSON.parse(object.toString());
            BasicDBObject searchQuery = new BasicDBObject().append("_id", objectToUpdate.get("_id"));
            WriteResult writeResult = lists.update(searchQuery, objectToUpdate);
            if (writeResult.getN() == 0) {
             //   return null;
            }
          } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return object;
    }

    private static void generateRandomXY(JSONObject object) throws JSONException {
        Random rand = new Random();
        object.put("x", rand.nextInt(76) + 5);
        object.put("y", rand.nextInt(76) + 5);
    }
}
