package recipe.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;

import recipe.service.ContentService;
import recipe.service.IndexService;
import recipe.service.LikesService;
import recipe.service.MemberService;
import recipe.service.RecipeService;
import recipe.vo.Content;
import recipe.vo.MainRecipe;
import recipe.vo.Member;
import recipe.vo.Recipe;

@Controller
@RequestMapping("/auth")
@SessionAttributes("loginUser")
public class AuthController {
  @Autowired MemberService memberService;
  @Autowired IndexService indexService;
  @Autowired LikesService likeService;
  @Autowired RecipeService recipeService;
  @Autowired ContentService contentService;
  
  
  // index 페이지 카드 4개 뽑기
  @RequestMapping(value="detailIndex", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detailIndex() throws ServletException, IOException {
    List<MainRecipe> mainRecipe = indexService.retrieveIndex();
    for(MainRecipe m : mainRecipe) {
      m.setLikes(likeService.countAll(m.getBno())); //좋아요를 넣어준다.
    }
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("list", mainRecipe);
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="detailContent", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detailContent(int bno) throws ServletException, IOException {
    Recipe recipe = recipeService.retrieve(bno);
    recipeService.county(bno);
    List<Content> content = contentService.list(recipe.getBno());
    
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("list", content);
    
    return new Gson().toJson(paramMap);
  }
  
  //로그인 아이디, 비밀번호 확인
  @RequestMapping(value="/login", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String login(
      String id,
      String password,
      HttpSession session) {
    HashMap<String,Object> result = new HashMap<>();
    if (memberService.exist(id, password)) {
      result.put("status", "success");
      Member member = memberService.retrieveById(id);
      session.setAttribute("loginUser", member);
      System.out.println("-----------");
      System.out.println("로그인성공");
      System.out.println("-----------");
    } else { // 로그인 실패!
      result.put("status", "failure");
      System.out.println("-----------");
      System.out.println("로그인실패"); 
      System.out.println("-----------");
    }
    return new Gson().toJson(result);
  }
  
  // urlPage 수정 | 삭제 버튼 유무
  @RequestMapping(value="/update_delete1", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update_delete1(
      int bno,
      HttpSession session) {
    HashMap<String,Object> result = new HashMap<>();
    
    Recipe recipe = recipeService.retrieve(bno);
    int mno = recipe.getMno();
    System.out.println(mno);
    if(session.getAttribute("loginUser") != null) {
      Member member = (Member)session.getAttribute("loginUser");
      int m_mno = member.getNo();
      System.out.println("....");
      System.out.println(m_mno);
      if (mno == m_mno) {
        result.put("status", "success");
      } else { 
        result.put("status", "failure");
      }
    } else {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  //로그아웃(index 용)
  @RequestMapping("/logout")
  public String logout(HttpSession session, SessionStatus status) {
    status.setComplete(); // @SessionAttributes 로 관리하는 값 제거
    session.invalidate(); // HttpSession 객체 무효화시킨다.
                          // => invalidate()는 스프링에서 @SessionAttributes로
                          //    관리하는 값을 제거하지 못한다.
    System.out.println("-----------");
    System.out.println("로그아웃");
    System.out.println("-----------");
    return "redirect:../index.html";
  }
  
  //로그아웃(urlPage 용)
  @RequestMapping(value="/logout_url", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String logout_url(HttpSession session, SessionStatus status, int bno) {
    status.setComplete(); // @SessionAttributes 로 관리하는 값 제거
    session.invalidate(); // HttpSession 객체 무효화시킨다.
                          // => invalidate()는 스프링에서 @SessionAttributes로
                          //    관리하는 값을 제거하지 못한다.
    int no = bno;
    
    System.out.println("-----------");
    System.out.println("로그아웃");
    System.out.println("-----------");
    return "redirect:../urlPage.html?bno=" + no;
  }
  
  //로그아웃(search 용)
  @RequestMapping(value="/logout_search", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String logout_search(HttpSession session, SessionStatus status, String searchValue) {
    status.setComplete(); // @SessionAttributes 로 관리하는 값 제거
    session.invalidate(); // HttpSession 객체 무효화시킨다.
    // => invalidate()는 스프링에서 @SessionAttributes로
    //    관리하는 값을 제거하지 못한다.
    String search = searchValue;
    
    System.out.println("-----------");
    System.out.println("로그아웃");
    System.out.println("-----------");
    return "redirect:../search.html?value=" + search;
  }

  //로그인 상태 확인
  @RequestMapping(value="/log", produces="application/json;charset=UTF-8", method=RequestMethod.POST)
  @ResponseBody
  public String log(HttpSession session)
  throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    try {
      if (session.getAttribute("loginUser") != null) {
         result.put("status", "success");
         result.put("session", session.getAttribute("loginUser"));
         System.out.println("-----------");
         System.out.println("세션성공");
         System.out.println("-----------");
      }else if (session.getAttribute("loginUser") == null) {
         result.put("status", "failure");
         System.out.println("-----------");
         System.out.println("세션실패");
         System.out.println("-----------");
      }
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", "failure");
    }
    System.out.println(session.getAttribute("loginUser"));
    return new Gson().toJson(result);
  }
  
  /* 회원가입 */
  @RequestMapping(value="/add", method=RequestMethod.POST)
  public String add(String id, String email, String password) throws ServletException, IOException {

    Member member = new Member();
    member.setId(id);
    member.setEmail(email);
    member.setPassword(password);
    
    memberService.add(member);
    System.out.println("-----------");
    System.out.println("회원가입성공");
    System.out.println("-----------");
    return "redirect:../index.html";
  }
  
  @RequestMapping(value="/loging", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String loging(HttpSession session) {
    HashMap<String,Object> result = new HashMap<>();
    if ( session.getAttribute("loginUser") != null) {
      result.put("status", "success");
    } else {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
}
