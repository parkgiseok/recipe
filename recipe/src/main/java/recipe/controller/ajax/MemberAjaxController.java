package recipe.controller.ajax;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import recipe.service.MemberService;
import recipe.vo.Member;

@Controller
@RequestMapping("/ajax/member/")
public class MemberAjaxController {
  @Autowired MemberService memberService;
  @Autowired ServletContext servletContext;
  
  
  // 회원탈퇴
  @RequestMapping(value="delete", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String delete(int no, String password) 
      throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    Member member = memberService.retrieveByNo(no);
    
    try {
      if (member.getPassword().equals(password)) {
        memberService.delete(no);
        result.put("status", "success");
      } else {
        result.put("status", "fail");
      }
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  // 회원정보
  @RequestMapping(value="detail", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detail(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    Member member2 = memberService.retrieveByNo(member.getNo());
    
    return new Gson().toJson(member2);
  }
  
//회원정보 변경
 @RequestMapping(value="upload",
     method=RequestMethod.POST)
 public String upload(MultipartHttpServletRequest request, int no, String nick, String gender, String category, String description) throws ServletException, IOException {
   HashMap<String,Object> result = new HashMap<>();
   Member member = new Member();
   Member member1 = memberService.retrieveByNo(no);
   System.out.println("request" + request);
   System.out.println("member1" + member1.getPhoto());
   Map<String, MultipartFile> files = request.getFileMap();
   System.out.println(files);
   CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("uploadFile");
   int extPoint = cmf.getOriginalFilename().lastIndexOf(".");
   System.out.println("extPoint : " +  extPoint);
     if (extPoint > 0){
       String filename="";
       filename = System.currentTimeMillis() + cmf.getOriginalFilename().substring(extPoint);
       System.out.printf("새파일명=%s\n", filename);
       String realPath = servletContext.getRealPath("files/");
       System.out.printf("새 파일을 저장할 실제 경로=%s\n", realPath);
       try {
         cmf.transferTo(new File(realPath+filename));
         Thumbnails.of(realPath + filename).crop(Positions.TOP_CENTER).size(200, 200).toFile(new File(realPath+"/thumb/"+filename));
         member.setPhoto("../files/thumb/"+filename);
       } catch (Exception e) {
         e.printStackTrace();
       }
     } else {
       member.setPhoto(member1.getPhoto());
     }
     System.out.println("----");
     System.out.println("member"+ member.getPhoto());
     System.out.println("member1" + member1.getPhoto());
     
   member.setNo(no);
   member.setPassword(member1.getPassword());
   // 닉네임 설정
   if (nick.equals("")) {
     member.setNick(member1.getNick());
   } else if (nick.length() > 5){
     return new Gson().toJson(result);
   } else {
     member.setNick(nick);
   }
   // 성별 설정
   member.setGender(gender);
   // 카테고리 설정
   if (category.equals("")) {     
     member.setCategory(member1.getCategory());
   } else if (category.length() > 8) {
     return new Gson().toJson(result);
   } else {
     member.setCategory(category);
   }
   // 자기소개 설정
   if (description.equals("")) {
     member.setDescription(member1.getDescription());
   } else if (description.length() > 52) {
     return new Gson().toJson(result);
   } else {
     member.setDescription(description);
   }

   try {
     System.out.println("성공");
     memberService.change(member);
   } catch (Exception e) {
     e.printStackTrace();
     System.out.println("실패");
   }
   return "redirect: ../../index.html";
 }
  
  // 비밀번호 변경
  @RequestMapping(value="update1",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update1(int no, String password, String password1, String password2) throws ServletException, IOException {
    Member member = new Member();
    Member member1 = memberService.retrieveByNo(no);
    System.out.println("getPassword()" + member1.getPassword());
    member.setNo(no);
    member.setPassword(password1);
    member.setNick(member1.getNick());
    member.setGender(member1.getGender());
    member.setCategory(member1.getCategory());
    member.setDescription(member1.getDescription());
    member.setPhoto(member1.getPhoto());
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      if (member1.getPassword().equals(password) && !member1.getPassword().equals(password1)) {
        if (password1.equals(password2)) {
          memberService.change(member);
          result.put("status", "success");
        } else {
          result.put("status", "fail");
        }
      }
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list() throws ServletException, IOException {
    List<Member> member = memberService.list(0, 0);
    System.out.println(new Gson().toJson(member));
    return new Gson().toJson(member);
  }

  
  
  
/*
  // 회원정보 변경
  @RequestMapping(value="update",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update(int no, String nick, int age, String category, String description) throws ServletException, IOException {    
    Member member = new Member();
    Member member1 = memberService.retrieveByNo(no);
           
    member.setNo(no);
    member.setPassword(member1.getPassword());
    member.setNick(nick);
    member.setAge(age);
    member.setCategory(category);
    member.setDescription(description);

    HashMap<String,Object> result = new HashMap<>();
    try {
      memberService.change(member);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
*/
}