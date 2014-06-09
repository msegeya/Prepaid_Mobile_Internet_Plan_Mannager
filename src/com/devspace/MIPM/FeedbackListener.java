package com.devspace.MIPM;

import java.net.MalformedURLException;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;



import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.sms.MoSmsListener;
import hms.kite.samples.api.sms.SmsRequestSender;
import hms.kite.samples.api.sms.messages.MoSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsResp;

public class FeedbackListener implements MoSmsListener {

	@Override
	public void init() {
		
	}

	@Override
	public void onReceivedSms(MoSmsReq moSmsReq) {
		String message=moSmsReq.getMessage();
		String sourceaddress=moSmsReq.getSourceAddress();
		
		FeedbackService.addFeedback(sourceaddress, message);
		
		sendResponse(moSmsReq);
	}
	private void sendResponse(MoSmsReq moSmsReq){
		try{
		MtSmsReq mtSmsReq=new MtSmsReq();
		mtSmsReq.setDestinationAddresses(Arrays.asList(moSmsReq.getSourceAddress()));
		mtSmsReq.setMessage(Feedback.getInput(moSmsReq.getSourceAddress(), moSmsReq.getMessage()));
		mtSmsReq.setApplicationId("APP_000001");
		mtSmsReq.setPassword("passwd");
		
		
		SmsRequestSender requestSender = new SmsRequestSender(new URL("http://localhost:7000/sms/send"));
		MtSmsResp smsResp=requestSender.sendSmsRequest(mtSmsReq);
		System.out.println("Response"+smsResp);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
			
		 catch (SdpException e) {
			e.printStackTrace();
		}
		
	}
	public static boolean register(String mob){
		String sql="Insert into mipm_table values('"+mob+"')";
		int res=0;
		try {
			res = DBHandler.setData(DBConnection.getConnectionToDB(), sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res>0;
	}
	
	public static boolean search(String mob){
		boolean done=false;
		String sql="Select * from mipm_table where contact_no='"+mob+"'";
		try {
			ResultSet rs=DBHandler.getData(DBConnection.getConnectionToDB(), sql);
			if(rs.next()){
				done=true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}
}

