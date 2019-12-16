package wada1028.info3.oepnv_navigator.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.List;

import wada1028.info3.oepnv_navigator.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RequestQueue queue;
    //private static final String[] STOPS = new String[]{"Europaplatz","Markplatz","Herrenstraße"};
    private JSONArray jsonArray = jsonParse();
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        AutoCompleteTextView autoCompleteTextViewStart = root.findViewById(R.id.autoCompleteTextView_Starthaltestelle);
        AutoCompleteTextView autoCompleteTextViewZiel = root.findViewById(R.id.autoCompleteTextView_zielhaltestelle);

        // Server request
        queue = Volley.newRequestQueue(getContext());
            jsonParse();

        //Stopfinder Autocomplete

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, (List<String>) jsonArray);
        autoCompleteTextViewStart.setAdapter(arrayAdapter);
        autoCompleteTextViewZiel.setAdapter(arrayAdapter);
        return root;
    }
    public JSONArray jsonParse(){
        final JSONArray[] jsonArray = new JSONArray[1];
        String url = "http://smartmmi.demo.mentz.net/smartmmi/XML_STOPFINDER_REQUEST?outputFormat=rapidJson&type_sf=any&name_sf=hbf";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {
                    jsonArray[0] = response.getJSONArray("locations");
//                    for (int i=0; i < jsonArray.length();i++){
//                        JSONObject location = jsonArray.getJSONObject(i);
//                        String id = location.getString("id");
//                        String name = location.getString("name");
//                        String type =location.getString("type");
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        return jsonArray[0];
    }
}