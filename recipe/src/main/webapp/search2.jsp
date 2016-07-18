<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="recipe.vo.RecipeCard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
ArrayList<RecipeCard> list = new ArrayList<>();
list.add(new RecipeCard("평일", "img/profile.png", "img/1.jpg", 
		"아침부터 뻘건 떡볶이를 먹어보자!! 국물 자작한 떡볶이 만...", 30, 75, 9, "description1"));
list.add(new RecipeCard("기석", "img/profile.png", "img/1.jpg", 
		"오늘 뭐먹지의 불맛이 나는 오징어볶음 ^^",	10, 20, 7, "description2"));
list.add(new RecipeCard("현신", "img/profile.png", "img/1.jpg", 
		"매콤한 무 고등어조림 만들기", 10, 30, 6, "description3"));
list.add(new RecipeCard("기학", "img/profile.png", "img/1.jpg", 
		"매콤한 견과류 닭강정 만드는법",	15, 50, 5, "description4"));
HashMap<String,Object> result = new HashMap<>();
result.put("list", list);
%>
<%=new Gson().toJson(result)%>











