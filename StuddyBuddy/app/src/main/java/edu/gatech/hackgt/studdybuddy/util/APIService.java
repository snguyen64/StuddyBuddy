package edu.gatech.hackgt.studdybuddy.util;

import java.util.List;

import edu.gatech.hackgt.studdybuddy.model.APIMessage;
import edu.gatech.hackgt.studdybuddy.model.CourseType;
import edu.gatech.hackgt.studdybuddy.model.LoginUser;
import edu.gatech.hackgt.studdybuddy.model.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Path;

public interface APIService {

    @POST("auth/login/")
    Call<APIMessage> login(@Body LoginUser lu);

    @POST("auth/register/")
    Call<APIMessage> register(@Body User user);

    @POST("/courses/{type}/")
    Call<List<Integer>> getCoursesByType(@Path("type") String type);
}
