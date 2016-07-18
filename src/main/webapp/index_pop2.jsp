<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="recipe.vo.index_pop"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
ArrayList<index_pop> list = new ArrayList<>();
list.add(new index_pop("img/1.jpg", "11111111   소중한 사람과 나눠 먹는다면 더할 나위 없이 좋은 간식이 될 딸기치즈타르트입니다. 크림치즈 필링을 듬뿍 올려 진하고 새콤달콤한 딸기와의 조화가 너무 멋스럽고 맛스럽답니다. 연말을 맞아 감사한 사람들에게 딸기치즈타르트를 선물해보세요. 잊지 못할 선물이 될 거예요."));
list.add(new index_pop("img/2.jpg", "22222222   소중한 사람과 나눠 먹는다면 더할 나위 없이 좋은 간식이 될 딸기치즈타르트입니다. 크림치즈 필링을 듬뿍 올려 진하고 새콤달콤한 딸기와의 조화가 너무 멋스럽고 맛스럽답니다. 연말을 맞아 감사한 사람들에게 딸기치즈타르트를 선물해보세요. 잊지 못할 선물이 될 거예요."));
list.add(new index_pop("img/3.jpg", "33333333   소중한 사람과 나눠 먹는다면 더할 나위 없이 좋은 간식이 될 딸기치즈타르트입니다. 크림치즈 필링을 듬뿍 올려 진하고 새콤달콤한 딸기와의 조화가 너무 멋스럽고 맛스럽답니다. 연말을 맞아 감사한 사람들에게 딸기치즈타르트를 선물해보세요. 잊지 못할 선물이 될 거예요."));

HashMap<String,Object> result = new HashMap<>();
result.put("list", list);
%>
<%=new Gson().toJson(result)%>
