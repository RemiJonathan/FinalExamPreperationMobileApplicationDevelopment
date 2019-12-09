package com.remijonathan.finalexampreperationmobileapplicationdevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private RequestQueue queue;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(this);
        database = db.getWritableDatabase();

        queue = Volley.newRequestQueue(this);

        jsonParse();

        recyclerView = findViewById(R.id.recycler_view_holiday_items);
    }

    private void jsonParse() {
        String url = "https://date.nager.at/api/v2/PublicHolidays/2019/ca";

        //In this case the data is an Array so we cal JsonArrayRequest,
        // if it was an Object we would use JsonObjectRequest. An empty request looks like this
        /*
        new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
         */

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject holiday = response.getJSONObject(i);

                                String name = holiday.getString("name");
                                String date = holiday.getString("date");

                                addHoliday(name, date);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        //Remember to add the request to the queue you created
        queue.add(request);
    }

    private void addHoliday(String name, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProjectContract.HolidaysEntry.COLUMN_NAME, name);
        contentValues.put(ProjectContract.HolidaysEntry.COLUMN_DATE, date);

        database.insert(ProjectContract.HolidaysEntry.TABLE_NAME, null, contentValues);
    }
}