//package si.uni_lj.fe.tnuv.tnuv_app;
//
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
////import org.json.simple.parser.JSONParser;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class JsonParser {
//
//
//
//    private static final String TAG = JsonParser.class.getSimpleName();
//    private ArrayList<HashMap<String, String>> vajeListData = new ArrayList<>();
//    private ArrayList<HashMap<String, String>> workoutListData = new ArrayList<>();
//
//
//    public ArrayList<HashMap<String, String>> parseToArrayList(String jsonStr){
//        try {
//            JSONObject jsonObj = new JSONObject(jsonStr);
//            //Object obj = parser.parse(new FileReader("//cdn.crunchify.com/Users/Shared/crunchify.json"));
//
//            // Getting JSON Array node
//            JSONArray vaje = jsonObj.getJSONArray("vaje");
//            JSONArray workout = jsonObj.getJSONArray("workout");
//
//
//            // looping through All Contacts
//            for (int i = 0; i < vaje.length(); i++) {
//                JSONObject c = vaje.getJSONObject(i);
//
//                String imeVaje = c.getString("imeVaje");
//                String muscleG = c.getString("muscleG");
//                String imgVaje = c.getString("imgVaje");
//                String cals = c.getString("cals");
//
//                // tmp hash map for single contact
//                HashMap<String, String> vaja = new HashMap<>();
//
//                // adding each child node to HashMap key => value
//                vaja.put("imeVaje", imeVaje);
//                vaja.put("muscleG", muscleG);
//                vaja.put("imgVaje", imgVaje);
//                vaja.put("cals", cals);
//
//                // adding contact to contact list
//                vajeListData.add(vaja);
//            }
//        } catch (final JSONException e) {
//            Log.e(TAG, "Json parsing error: " + e.getMessage());
//        }
//        return vajeListData;
//    }
//}
