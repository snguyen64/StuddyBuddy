package edu.gatech.hackgt.studdybuddy.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit;
    private static APIService service;

    public static APIService getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        if (service == null) {
            service = retrofit.create(APIService.class);
        }
        return service;
    }
}
