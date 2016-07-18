package recipe.controller.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import recipe.service.ReplyService;
import recipe.vo.Member;
import recipe.vo.Reply;
import recipe.vo.ReplyData;
import recipe.vo.ReplyRecipeMember;

@Controller
@RequestMapping("/ajax/reply/")
public class ReplyAjaxController {
  @Autowired
  ReplyService replyService;

  @RequestMapping(produces="application/json;charset=UTF-8", value="add")
  @ResponseBody
  public String add(HttpSession session, int bno, String r_cont) 
      throws ServletException, IOException {
    Reply reply = new Reply();
    HashMap<String,Object> result = new HashMap<>();
    Member member = ((Member)session.getAttribute("loginUser"));
    if ( member == null ) {
      result.put("status", "failure"); 
      return new Gson().toJson(result);
    } else if ( replyService.retrieveByNo(
        ((Member)session.getAttribute("loginUser")).getNo(), bno) != null) {
      result.put("status", "onlyone"); 
      return new Gson().toJson(result);
    }
    reply.setMno(member.getNo());
    reply.setBno(bno);
    reply.setR_cont(r_cont);
    try {
      result.put("status", "success");
      replyService.add(reply);
    } catch(Exception e) {
      result.put("status", "failure"); 
      e.printStackTrace();
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(produces="application/json;charset=UTF-8", value="delete")
  @ResponseBody
  public String delete(HttpSession session,int bno) throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    try {
      replyService.delete(((Member)session.getAttribute("loginUser")).getNo(),bno);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(produces="application/json;charset=UTF-8", value="list")
  @ResponseBody
  public String list(HttpSession session, int bno) throws ServletException, IOException {
    List<ReplyData> list = replyService.list(bno,1);
    HashMap<String,Object> result = new HashMap<>();
    try { 
      result.put("no", ((Member)session.getAttribute("loginUser")).getNo());
    } catch( Exception e ) {
      result.remove("no");
    }
    result.put("list", list);
    return new Gson().toJson(result);
  }

  @RequestMapping(method=RequestMethod.POST,
      produces="application/json;charset=UTF-8", value="update")
  @ResponseBody
  public String update(HttpSession session,int bno, String r_cont)
      throws ServletException, IOException {
    Reply reply = replyService.retrieveByNo(
        ((Member)session.getAttribute("loginUser")).getNo(), bno);
    reply.setR_cont(r_cont);
    HashMap<String, Object> result = new HashMap<>();
    try {
      replyService.change(reply);
      result.put("status", "success");
    } catch(Exception e) {
      result.put("status", "failure");
      e.printStackTrace();
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="replyRecipeRecent", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String replyRecipeRecent(
      @RequestParam(defaultValue="0") int pageNo, 
      @RequestParam(defaultValue="4") int pageSize, HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");        
    
    // 페이지 번호와 페이지 당 출력 개수의 유효성 검사
    
    int totalPage = replyService.countPage(pageSize);
    if (pageNo > totalPage) { // 가장 큰 페이지 번호를 넘지 않게 한다.
    }
    
    if (pageSize < 4) { // 최소 3개
      pageSize = 4; 
    } else if (pageSize > 50) { // 최대 50개 
      pageSize = 50;
    }
    List<ReplyRecipeMember> recipe = replyService.replyRecipeRecent(member.getNo(),pageNo,pageSize);
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("list", recipe);
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="count", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String count(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");        
    
    //List<RecipeMember> countOfRecipe = countOfRecipe.set(index, element);
    int countOfRecipe = replyService.county(member.getNo());
    
    HashMap<String, Object> paramMap = new HashMap<>();
    
    paramMap.put("count", countOfRecipe);
    return new Gson().toJson(paramMap);
  }
}