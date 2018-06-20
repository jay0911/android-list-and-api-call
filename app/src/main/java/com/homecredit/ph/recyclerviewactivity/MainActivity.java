package com.homecredit.ph.recyclerviewactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

/*        StringRequest req = new StringRequest(Request.Method.GET,
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
        requestQueue.add(req);*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SuperHeroApi service = retrofit.create(SuperHeroApi.class);

        Call<SuperHeroesDto> superHeroes = service.getSuperHeroes();

                superHeroes.enqueue(new Callback<SuperHeroesDto>() {
                    @Override
                    public void onResponse(Call<SuperHeroesDto> call, retrofit2.Response<SuperHeroesDto> response) {

                        List<SuperHero> heroes = response.body().getHeroes();

                        for(SuperHero u : heroes){
                            listItems.add(new ListItem(u.getName(), u.getAbout(), u.getImage()));
                        }

                        adapter = new MyAdapter(listItems,getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailure(Call<SuperHeroesDto> call, Throwable err) {
                        Toast.makeText(getApplicationContext(),err.getMessage(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        System.out.print(err.getMessage());
                    }
                });
    }
}
