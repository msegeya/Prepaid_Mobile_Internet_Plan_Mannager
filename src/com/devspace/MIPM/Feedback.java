package com.devspace.MIPM;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.subscription.SubscriptionRequestSender;
import hms.kite.samples.api.subscription.messages.SubscriptionRequest;
import hms.kite.samples.api.subscription.messages.SubscriptionResponse;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

//import sun.security.mscapi.KeyStore.MY;

public class Feedback {
	public static boolean a=false;
	public static boolean b=false;
	public static boolean backCard=false;
	public static boolean backDaily=false;
	public static boolean backKnktd=false;
	public static boolean backTime=false;
	public static boolean backMonthly=false;
	
	public static boolean backNight=false;
	public static boolean card=false;
	/*public static boolean backDaily1=false;
	public static boolean backKnktd1=false;
	public static boolean backTime1=false;
	public static boolean backMonthly1=false;
	public static boolean backNight1=false;*/
	private static final ConcurrentHashMap<String, String> sessions= new ConcurrentHashMap<String,String>();

public static String getInput(String mobNo,String input){
	
	if("MIPM".equals(input)|| "mipm".equals(input)){
		sessions.put(mobNo,"welcome");
		return "Welcome to the Service..!!\nMIPM 77.Register\nMIPM 00.Start";
	}else if("welcome".equals(sessions.get(mobNo))){
		input=input.substring(5);
		if("77".equals(input))
		{
			
			
			a=true;
			SubscriptionRequest subscriptionRequest=new SubscriptionRequest();
			subscriptionRequest.setApplicationId("APP_000101");
			subscriptionRequest.setPassword("passwd");
			subscriptionRequest.setSubscriberId(mobNo);
			subscriptionRequest.setAction("1");
			
			try {
				SubscriptionRequestSender subscriptionRequestSender=new SubscriptionRequestSender(new URL("http://localhost:7000/subscription/send"));
				SubscriptionResponse subscriptionResponse=subscriptionRequestSender.sendSubscriptionRequest(subscriptionRequest);
				
				if("S1000".equals(subscriptionResponse.getStatusCode())){
					a=true;
					return "Registration was Successful\nMIPM 00.Start";
					
				}else if("E1351".equals(subscriptionResponse.getStatusCode())){
					a=true;
					return "You've already registered\nMIPM 00.Start";
				}
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (SdpException e) {
				e.printStackTrace();
			}
			
		}/*else if("00".equals(input)){
			if(FeedbackListener.register(mobNo)){
				return myPlan();
			}else{
				return "You've not registered...\n Please type 77 to register";
			}
		}*/
		
		
	}if("00".equals(input)&& a){
		b=true;
		return myPlan(); 
	}
	if((backCard&&"88".equals(input))||(backDaily&&"88".equals(input))|| (backKnktd&&"88".equals(input))|| (backMonthly&&"88".equals(input)) ||(backNight&&"88".equals(input))|| (backTime&&"88".equals(input))){
		return myPlan();
	}
	//if((b&&(((input=="1" || input=="2")||(input=="3" || input=="4"))|| (input=="5" || input=="6"))))
	if((("6".equals(input)||"5".equals(input))||("4".equals(input)||"3".equals(input))||("2".equals(input)||"1".equals(input)))&& b)
	{	
	return internet(input);
	}
	if(backCard){
		return cardActivation(input)+"\n"+myPlan();
	}
	if(backDaily){
		return dailyActivation(input)+"\n"+myPlan();
	}
	if(backKnktd){
		return knktdActivation(input)+"\n"+myPlan();
	}
	if(backMonthly){
		return monthlyActivation(input)+"\n"+myPlan();
	}
	if(backNight){
		return nightActivation(input)+"\n"+myPlan();
	}
	if(backTime){
		return timeActivation(input)+"\n"+myPlan();
	}
	return "";
}
	public static String myPlan(){ 
	String pac="MIPM 1.Internet cards\nMIPM 2.Daily Rental Packages\nMIPM 3.KNKTD packages\nMIPM 4.Time Based package\nMIPM 5.Monthly Package\n6.Night time package";
	return pac;
	}
	
	public static String internet(String n)
	{
		switch(n){
		case "1":return Card();
		case "2":return daily();
		case "3":return knktd();
		case "4":return time();
		case "5":return monthly();
		case "6":return night();
		
		default:
			return "";
		}
	}
	
	public static String cardActivation(String n){
		if((("15".equals(n))||("14".equals(n)||"13".equals(n))||("12".equals(n)||"11".equals(n)))){
			return "Your package is activated";
		}//else if(n!="88"){
			//return "Invalid number\n"+Card();
		return "";
		}//else{
			//return "";
		//}
	
	
	public static String dailyActivation(String n){
		if(("21".equals(n))||("22".equals(n))){
			return "Your package is activated";
		}else if(n!="88"){
			return "Invalid number\n"+daily();
		}else{
			return "";
		}
	}
	public static String knktdActivation(String n){
		if(("31".equals(n))||("32".equals(n))||"33".equals(n)){
			return "Your package is activated";
		}else if(n!="88"){
			return "Invalid number\n"+knktd();
		}else{
			return "";
		}
	}
	public static String timeActivation(String n){
		if(("41".equals(n))||("42".equals(n))||"43".equals(n)||"44".equals(n)||"45".equals(n)){
			return "Your package is activated";
		}else if(n!="88"){
			return "Invalid number\n"+time();
		}else{
			return "";
		}
	}
	public static String monthlyActivation(String n){
		if("451".equals(n)){
			return "Your package is activated";
		}else if(n!="88"){
			return "Invalid number\n"+monthly();
		}else{
			return "";
		}
	}
	public static String nightActivation(String n){
		if(("61".equals(n))||("62".equals(n))||"63".equals(n)){
			return "Your package is activated";
		}else if(n!="88"){
			return "Invalid number\n"+night();
		}else{
			return "";
		}
	}
	public static String Card()
	{	
		backCard=true;
		String card="MIPM 11.Rs.29  -100MB  -3days\nMIPM 12.Rs.49  -200MB  -7day\nMIPM 13.Rs.99  -550MB  -21days\nMIPM 14.Rs.299 -1530MB -30days\nMIPM 15.Rs.699 -5120MB -30days\nMIPM 88.Back";
		return card;
	}
	
	public static String daily(){
		backDaily=true;
		String daily="MIPM 21.Rs.3  20MB \nMIPM 22.Rs.20 100MB\nMIPM 88.Back";
		return daily;
	}
	public static String knktd()
	{	
		backKnktd=true;
		String knktd="MIPM 31.Rs.39  youtube-200MB Data-100MB\nMIPM 32.Rs.89  youtube-500MB Data-250MB\nMIPM 33.Rs.159 youtube-900MB Data-450MB\nMIPM 88.Back";
		return knktd;
	}
	public static String time(){
		backTime=true;
		String time="MIPM 41.30min   Rs.20  1day unlimited\nMIPM 42.2hours  Rs.50  3day unlimited\nMIPM 43.5hours  Rs.100 5day 500MB\nMIPM 44.12hours Rs.200 5day 1GB \nMIPM 45.24hours Rs.400 7day 2GB\nMIPM 88.Back";
		return time;
	}
	public static String monthly(){
		backMonthly=true;
		String monthly="MIPM 51.Rs.299 1GB\nMIPM 88.Back";
		return monthly;
	}
	public static String night(){
		backNight=true;
		String night="MIPM 61.Rs.100 Unlimited\nMIPM 62.Rs.50  3GB\nMIPM 63.Rs.20  1GB\nMIPM 88.Back";
		return night;
	}
	
}