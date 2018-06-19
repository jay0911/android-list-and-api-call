package com.homecredit.ph.recyclerviewactivity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://10.0.2.2:8080/heroes";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        load();
    }

    private void load(){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        StringRequest req = new StringRequest(Request.Method.GET,
                URL_DATA,
                (resp)->{
                    try{
                        JSONObject jsonObject = new JSONObject(resp);
                        JSONArray array = jsonObject.getJSONArray("heroes");

                        for(int i=0;i<array.length();i++){
                            JSONObject o = array.getJSONObject(i);
                            ListItem item = new ListItem(o.getString("name"), o.getString("about"), o.getString("image"));
                            listItems.add(item);
                        }

                        adapter = new MyAdapter(listItems,getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                },
                (err)->{
                    Toast.makeText(getApplicationContext(),err.getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }
}
