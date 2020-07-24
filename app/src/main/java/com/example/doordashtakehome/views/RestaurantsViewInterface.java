package com.example.doordashtakehome.views;

import com.example.doordashtakehome.models.Restaurant;

import java.util.List;


public interface RestaurantsViewInterface {
    void onFetchSuccess(List<Restaurant> restaurantList);
    void onFetchError();
}
