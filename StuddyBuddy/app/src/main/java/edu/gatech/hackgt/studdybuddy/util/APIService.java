package edu.gatech.hackgt.studdybuddy.util;

import edu.gatech.hackgt.studdybuddy.model.APIMessage;
import edu.gatech.hackgt.studdybuddy.model.LoginUser;
import edu.gatech.hackgt.studdybuddy.model.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.Call;

public interface APIService {
    @POST("auth/login")
    Call<User> login(@Body LoginUser lu);

    @POST("auth/register")
    Call<APIMessage> register(@Body User user);
}
