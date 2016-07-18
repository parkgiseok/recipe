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
list.add(new RecipeCard("김평일", "img/han.jpg", "img/1.jpg", 
		"베이킹 레시피 : 딸기 타르트 만들기", 30, 75, 9, ""));
list.add(new RecipeCard("박기석", "img/profile.png", "img/2.jpg", 
		"초간단 10분 완성 알리오올리오 만들기",	10, 20, 7, ""));
list.add(new RecipeCard("박현신", "img/han.jpg", "img/3.jpg", 
		"토핑 얹은 맛있는 계란말이 만들기", 10, 30, 6, ""));
list.add(new RecipeCard("신기학", "img/profile.png", "img/1.jpg", 
		"토마토 소스 김치 스파게티 안 만들면 손해!!",	15, 50, 5, ""));
HashMap<String,Object> result = new HashMap<>();
result.put("list", list);
%>
<%=new Gson().toJson(result)%>
