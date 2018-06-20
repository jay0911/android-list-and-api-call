package com.homecredit.ph.recyclerviewactivity;


import retrofit2.Call;
import retrofit2.http.GET;

public interface SuperHeroApi {

    @GET("heroes")
    Call<SuperHeroesDto> getSuperHeroes();
}
