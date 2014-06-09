<%@page import="com.devspace.MIPM.FeedbackService"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
		<th>SubscriberId Number</th>
		<th>Message</th>
		</tr>
		<%
		Map<String,String>feedback=FeedbackService.getFeedback();
		for(String subscribeId:feedback.keySet()){
			out.println("<tr><td>"+subscribeId+"</td><td>"+feedback.get(subscribeId)+"</td></tr>");
		}
		%>
	</table>
</body>
</html>