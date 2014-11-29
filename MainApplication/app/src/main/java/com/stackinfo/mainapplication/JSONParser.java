package com.stackinfo.mainapplication;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class JSONParser {
	
	public static List<selfie> parseFeed(String content) {
	
		try {
			Log.d("try", "json");
			JSONObject jobj=new JSONObject(content);			
			JSONArray ar = jobj.getJSONArray("data");
			Log.d("try", "json1");
			List<selfie> flowerList = new ArrayList<selfie>();
			Log.d("try", "json2");
			for (int i = 0; i < ar.length(); i++) {
				Log.d("try", "json3");
				JSONObject obj = ar.getJSONObject(i);
				JSONObject obj2=obj.getJSONObject("images");
				JSONObject obj3=obj2.getJSONObject("low_resolution");
				JSONObject obj4=obj.getJSONObject("user");
				JSONObject obj5=obj2.getJSONObject("standard_resolution");
				selfie selfie = new selfie();
				Log.d("try", "loop");
//				flower.setProductId(obj.getInt("productId"));
				selfie.setName(obj4.getString("username"));
//				selfie.setCategory(obj.getString("category"));
				selfie.setInstructions(obj5.getString("url"));
				selfie.setPhoto(obj3.getString("url"));
//				selfie.setPrice(obj.getDouble("price"));
				
				flowerList.add(selfie);
			}
			
			return flowerList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
