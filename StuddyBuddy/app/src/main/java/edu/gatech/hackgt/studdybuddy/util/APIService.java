package edu.gatech.hackgt.studdybuddy.util;

import java.util.List;

import edu.gatech.hackgt.studdybuddy.model.APIMessage;
import edu.gatech.hackgt.studdybuddy.model.ChatMessage;
import edu.gatech.hackgt.studdybuddy.model.Chatroom;
import edu.gatech.hackgt.studdybuddy.model.Course;
import edu.gatech.hackgt.studdybuddy.model.CourseType;
import edu.gatech.hackgt.studdybuddy.model.LoginUser;
import edu.gatech.hackgt.studdybuddy.model.User;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Path;

public interface APIService {

    @POST("auth/login/")
    Call<APIMessage> login(@Body LoginUser lu);

    @POST("auth/register/")
    Call<APIMessage> register(@Body User user);

    @POST("courses/{type}/")
    Call<List<Integer>> getCoursesByType(@Path("type") String type);

    @POST("course/{id}/store/")
    Call<APIMessage> storeCourse(@Path("id") int userid, @Body Course course);

    @GET("courses/{id}/")
    Call<List<Course>> getCourses(@Path("id") int userid);

    @DELETE("course/{id}/delete/{type}/{number}/")
    Call<APIMessage> deleteCourse(@Path("id") int userid, @Path("type") String type, @Path("number") int number);

    @POST("chatroom/create/{id}/")
    Call<APIMessage> createChatroom(@Body Chatroom chatroom, @Path("id") int userid);

    @POST("chatroom/{name}/join/{id}/")
    Call<APIMessage> joinChatroom(@Path("name") String name, @Path("id") int userId);

    @POST("chatroom/list/")
    Call<List<Chatroom>> getListofChatroomsByCourse(@Body Course course);

    @GET("chatroom/messages/{name}")
    Call<List<ChatMessage>> getChatMessages(@Path("name") String name);
}
