package com.devspace.MIPM;

import java.util.HashMap;
import java.util.Map;

public class FeedbackService {
public static final Map<String,String> subscriberfeedback=new HashMap<String,String>();
	
	public static void addFeedback(String subscriberId,String message){
		subscriberfeedback.put(subscriberId, message);
	}
	
	public static Map<String,String> getFeedback(){
		return subscriberfeedback;
	}
}
