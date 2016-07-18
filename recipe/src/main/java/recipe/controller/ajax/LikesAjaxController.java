package recipe.controller.ajax;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import recipe.service.LikesService;
import recipe.vo.Likes;
import recipe.vo.Member;

@Controller
@RequestMapping("/ajax/likes/")
public class LikesAjaxController {
  @Autowired LikesService likesService;

  //좋아요 추가
  @RequestMapping(produces="application/json;charset=UTF-8", value="add")
  @ResponseBody
  public String add(HttpSession session, int bno)
      throws ServletException, IOException {
    Likes likes = new Likes();
    likes.setMno(((Member)session.getAttribute("loginUser")).getNo());
    likes.setBno(bno);
    HashMap<String,Object> result = new HashMap<>();
    try {
      result.put("status", "success");
      likesService.add(likes);
    } catch(Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }

  //좋아요 취소
  @RequestMapping(produces="application/json;charset=UTF-8", value="delete")
  @ResponseBody
  public String delete(HttpSession session,int bno) throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    try {
      likesService.delete(((Member)session.getAttribute("loginUser")).getNo(),bno);
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
      Likes likes = (Likes)likesService.retrieveByNO(((Member)session.getAttribute("loginUser")).getNo(),bno);
      result.put("mno", likes.getMno());
      result.put("bno", likes.getBno());
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
    return  new Gson().toJson(likesService.countAll(bno));
  }
  
  //좋아요 선택 여부
  @RequestMapping(produces="application/json;charset=UTF-8", value="islike")
  @ResponseBody
  public boolean islike(HttpSession session, int bno) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    //System.out.println("isSe:" +member); 멤버의 세션값
    return likesService.isLike(bno, member.getNo());
  }
  
}
