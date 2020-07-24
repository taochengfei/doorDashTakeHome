package com.example.doordashtakehome.presenter;

import android.util.Log;

import com.example.doordashtakehome.models.Restaurant;
import com.example.doordashtakehome.network.RestaurantService;
import com.example.doordashtakehome.views.RestaurantsViewInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class RestaurantPresenter implements RestaurantPresenterInterface {

    protected static final String TAG = RestaurantPresenter.class.getSimpleName();

    private RestaurantsViewInterface viewInterface;
    private RestaurantService restaurantService;
    private CompositeDisposable compositeDisposable = null;
    private Scheduler backgroundScheduler;
    private Scheduler mainscheduler;


    public RestaurantPresenter(RestaurantsViewInterface viewInterface, RestaurantService
            restaurantService, Scheduler backgroundScheduler, Scheduler mainScheduler){
        this.viewInterface = viewInterface;
        this.restaurantService = restaurantService;
        compositeDisposable = new CompositeDisposable();
        this.backgroundScheduler = backgroundScheduler;
        this.mainscheduler = mainScheduler;
    }

    Double latitude = 37.422740;
    Double longitude = -122.139956;




    @Override
    public void fetchRestaurants() {

        Observable<List<Restaurant>>restaurantObservable = restaurantService.getRestaurants(latitude,
                longitude, 0, 50);

        compositeDisposable.add(restaurantObservable.subscribeOn(backgroundScheduler)
                .observeOn(mainscheduler)
                .subscribeWith(new DisposableObserver<List<Restaurant>>() {
                    @Override
                    public void onNext(List<Restaurant> restaurants) {
                        viewInterface.onFetchSuccess(restaurants);
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewInterface.onFetchError();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed");
                    }
                }));


    }

    @Override
    public void onDestroy() {
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }
}
