<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>



<script type="text/javascript">

	function changeImg(){
		  document.getElementById("randomcode_img").src="${pageContext.request.contextPath }/validatecode.jsp?t="+Math.random();
	}
	
</SCRIPT>

</head>
<body>
<form action="${pageContext.request.contextPath }/login.do" method="post" >
	用户名：<input type="text" name="username"/><br/>
	密&nbsp 码:<input type="text" name="password"/><br/>
	<input type="submit" value="登录"  ><br/>
	<input type="checkbox" name="rememberMe"  />自动登陆
	<br/>
	
	验证码：<input id="randomcode" name="randomcode" size="8" />
	 <img id="randomcode_img" src="${pageContext.request.contextPath }/validatecode.jsp" alt="" width="56" height="20" align='absMiddle' /> 
	<a href="javascript:changeImg();" >刷新</a>
</form>
</body>
