package recipe.controller.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import recipe.service.FavoriteService;
import recipe.vo.Favorite;
import recipe.vo.Member;
import recipe.vo.RecipeMember;

@Controller
@RequestMapping("/ajax/favorite/")
public class FavoriteAjaxController {
  @Autowired FavoriteService favoriteService;

  //북마크 추가
  @RequestMapping(produces="application/json;charset=UTF-8", value="add")
  @ResponseBody
  public String add(HttpSession session, int bno)
      throws ServletException, IOException {
    Favorite favorite = new Favorite();
    favorite.setMno(((Member)session.getAttribute("loginUser")).getNo());
    favorite.setBno(bno);
    HashMap<String,Object> result = new HashMap<>();
    try {
      result.put("status", "success");
      favoriteService.add(favorite);
    } catch(Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }

  //북마크 취소
  @RequestMapping(produces="application/json;charset=UTF-8", value="delete")
  @ResponseBody
  public String delete(HttpSession session,int bno) throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    try {
      favoriteService.delete(((Member)session.getAttribute("loginUser")).getNo(),bno);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }

  
  @RequestMapping(produces="application/json;charset=UTF-8", value="byno")
  @ResponseBody
  public String bymno(HttpSession session,int bno)
      throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    try {
      Favorite favorite = (Favorite)favoriteService.retrieveByNO(((Member)session.getAttribute("loginUser")).getNo(),bno);
      result.put("mno", favorite.getMno());
      result.put("bno", favorite.getBno());
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }

  //좋아요 개수
  @RequestMapping(produces="application/json;charset=UTF-8", value="count")
  @ResponseBody
  public String count(int bno)
      throws ServletException, IOException {
    return  new Gson().toJson(favoriteService.countAll(bno));
  }
  
  //북마크 여부
  @RequestMapping(produces="application/json;charset=UTF-8", value="isFavorite")
  @ResponseBody
  public boolean isFavorite(HttpSession session, int bno) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    //System.out.println("isSe:" +member); 멤버의 세션값
    return favoriteService.isFavorite(bno, member.getNo());
  }

  @RequestMapping(value="detailRecent", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detailRecent(
      @RequestParam(defaultValue="1") int pageNo, 
      @RequestParam(defaultValue="4") int pageSize, HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");   
    int mno = member.getNo();
    
    // 페이지 번호와 페이지 당 출력 개수의 유효성 검사
    if (pageNo < 0) { // 1페이지 부터 시작
      pageNo = 1;
    }
    
    int totalPage = favoriteService.countPage(pageSize, mno);
    if (pageNo > totalPage) { // 가장 큰 페이지 번호를 넘지 않게 한다.
    }
    // System.out.println(totalPage);
    
    if (pageSize < 4) { // 최소 4개
      pageSize = 4; 
    } else if (pageSize > 50) { // 최대 50개 
      pageSize = 50;
    }
    
    List<RecipeMember> recipe = favoriteService.retrieveRecent(member.getNo(),pageNo,pageSize);
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("mno", mno);
    paramMap.put("pageNo", pageNo);
    paramMap.put("pageSize", pageSize);
    paramMap.put("totalPage", totalPage);
    paramMap.put("list", recipe);
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="countRecipeFavorite", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String countRecipeFavorite(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");        
    
    //List<RecipeMember> countOfRecipe = countOfRecipe.set(index, element);
    int countOfFavorite = favoriteService.countyRecipeFavorite(member.getNo());
    
    HashMap<String, Object> paramMap = new HashMap<>();
    
    paramMap.put("count", countOfFavorite);
    return new Gson().toJson(paramMap);
  }
}
