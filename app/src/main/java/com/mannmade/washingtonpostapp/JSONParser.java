package com.mannmade.washingtonpostapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Eg0 Jemima on 5/20/2016.
 */
public class JSONParser {//Singleton Class to pass JSON String provided
    //member variables and constructor need to be private for singletons! Only allow others to access needed getters
    private static JSONParser mInstance = null;
    public ArrayList<PostObject> jsonArrayList;

    //private constructor
    private JSONParser(){}

    public static JSONParser getInstance(){
        if(mInstance == null){
            mInstance = new JSONParser();
        }
        return mInstance;
    }

    public void getJSONforString(String json){
        //One Array list to house all mappings of key value pairs
        jsonArrayList = new ArrayList<>();

        try{
            //create JSON Object
            //JSONObject readObject = new JSONObject(json);
            JSONObject readObject = new JSONObject(json);
            JSONArray postArray = readObject.getJSONArray("posts");
            //loop thru each item in jsonArray and store key value pairs in map for each object
            for(int i = 0; i < postArray.length(); i++){
                JSONObject jsonItem = postArray.getJSONObject(i);
                Log.i("Listing Items", "Item " + i);
                //Iterator<String> keys = jsonItem.keys();
                PostObject post;
                String title = jsonItem.getString("title");
                String date = jsonItem.getString("date");
                String content = jsonItem.getString("content");

                post = new PostObject(title, date, content);
                Log.i("Json Item", post.title + " " + post.date);
                jsonArrayList.add(post);
            }
        }catch(Exception e){
            Log.e("WashingtonPostApp", "JsonParsingError", e);
        }
    }
}
