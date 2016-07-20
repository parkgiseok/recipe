package recipe.controller.ajax;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import recipe.service.ContentService;
import recipe.service.FoodService;
import recipe.service.LikesService;
import recipe.service.MemberService;
import recipe.service.RecipeService;
import recipe.service.TagService;
import recipe.vo.Content;
import recipe.vo.Food;
import recipe.vo.Member;
import recipe.vo.Recipe;
import recipe.vo.RecipeMember;
import recipe.vo.SearchedRecipe;
import recipe.vo.Tag;

@Controller
@RequestMapping("/ajax/recipe/")
public class RecipeAjaxController {
  @Autowired RecipeService recipeService;
  @Autowired MemberService memeberService;
  @Autowired ContentService contentService;
  @Autowired LikesService likeService;
  @Autowired FoodService foodService;
  @Autowired TagService tagService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping(value="add", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String add(HttpSession session,String title,String m_photo, int c_nara, String c_situ, int c_cook, int c_food, int time, int level) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    
    Recipe recipe = new Recipe();
    
    String extension = "." + m_photo.split(";")[0].split("/")[1];
    
    String photoData = m_photo.split(",")[1];
    
    byte[] data = Base64.decodeBase64(photoData);
    String filename = "";
    filename = System.currentTimeMillis() + extension;
    
    String realPath = servletContext.getRealPath("files/");
    try (OutputStream stream = new FileOutputStream(realPath + filename)) {
      stream.write(data);
      Thumbnails.of(realPath + filename).crop(Positions.TOP_CENTER).size(700, 500).toFile(new File(realPath+"/thumb/"+filename));
      stream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    recipe.setMno(member.getNo());
    recipe.setTitle(title);
    recipe.setM_photo("../files/thumb/"+filename);
    recipe.setC_nara(c_nara);
    recipe.setC_situ(c_situ);
    recipe.setC_cook(c_cook);
    recipe.setC_food(c_food);
    recipe.setTime(time);
    recipe.setLevel(level);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      recipeService.add(recipe);
      result.put("status", "success");
      System.out.println(" 성공ㅋㅋ");
    } catch(Exception e) {
      result.put("status", "failure");
      System.out.println("실패ㅋㅋ");
    }
    
    return new Gson().toJson(result);
  }

  @RequestMapping(value="delete", 
      method=RequestMethod.POST, produces="application/json;charset=UTF-8")
  @ResponseBody
  public String delete(int bno) 
      throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    try{
      recipeService.delete(bno);
      result.put("status", "success");
    } catch(Exception e) {
      e.printStackTrace();
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
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
  
  @RequestMapping(value="detail", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detail(int bno) throws ServletException, IOException {
    /*
    int totalPage = recipeService.countPage(pageSize);
    if (pageNo > totalPage) { // 가장 큰 페이지 번호를 넘지 않게 한다.
    }
    
    if (pageSize < 4) { // 최소 3개
      pageSize = 4; 
    } else if (pageSize > 50) { // 최대 50개 
      pageSize = 50;
    }*/
    Recipe recipe = recipeService.retrieve(bno);
    List<Recipe> recipe1 = recipeService.retrieveListByMno(recipe.getMno());
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("list", recipe1);
    
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="selectTagList", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
  @ResponseBody
  public String selectTagList(int bno, HttpSession session) throws ServletException, IOException {    
    @SuppressWarnings("unused")
    Member member = (Member)session.getAttribute("loginUser");    
    List<Tag> tags = recipeService.selectTagList(bno);
    /*
    for (int i = 0; i < 5; i++) {
      System.out.print(tags.get(i).getTname() + ", ");
    }
    System.out.println();
    */
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("list", tags);    
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="detailMember", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detailMember(Integer bno) throws ServletException, IOException {
    Recipe recipe = recipeService.retrieve(bno);
    Member member = memeberService.retrieveByNo(recipe.getMno());
    
    return new Gson().toJson(member);
  }
  
  @RequestMapping(value="detailRecent", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detailRecent(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");    
    List<RecipeMember> recipe = recipeService.retrieveRecent(member.getNo());
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("list", recipe);
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="detailRecipeControl", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detailRecipeControl(
      @RequestParam(defaultValue="0") int pageNo, 
      @RequestParam(defaultValue="4") int pageSize, HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");        
    
    // 페이지 번호와 페이지 당 출력 개수의 유효성 검사
    
    int totalPage = recipeService.countPage(pageSize);
    if (pageNo > totalPage) { // 가장 큰 페이지 번호를 넘지 않게 한다.
    }
    
    if (pageSize < 4) { // 최소 3개
      pageSize = 4; 
    } else if (pageSize > 50) { // 최대 50개 
      pageSize = 50;
    }
    List<RecipeMember> recipe = recipeService.retrieveRecipeControl(member.getNo(),pageNo,pageSize);
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("list", recipe);
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="searchRecipeByNick", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String searchRecipeByNick(
      @RequestParam(defaultValue="0") int pageNo, 
      @RequestParam(defaultValue="4") int pageSize, HttpSession session, String nick) throws ServletException, IOException {    
    
    @SuppressWarnings("unused")
    Member member = (Member)session.getAttribute("loginUser");    
    
    if (pageSize < 4) { // 최소 3개
      pageSize = 4; 
    } else if (pageSize > 50) { // 최대 50개 
      pageSize = 50;
    }
    List<SearchedRecipe> recipe = recipeService.searchByNick(nick, pageNo, pageSize);
    for(SearchedRecipe r : recipe) {
      r.setLikes(likeService.countAll(r.getBno())); //좋아요를 넣어준다.
    }
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("list", recipe);    
    
    String json = new Gson().toJson(paramMap);
//    String[] tags = json.split("\"tname\":\"");
    System.out.println(json);
    
 /*   json = json.split("\"tname\":\"")[0] + "\"tname\":\"";
    for (int i = 1; i < tags.length; i++) {
      json = json + "#" + tags[i].split("\"")[0]+ " ";
    }
    json = json + "\"}]}";
    System.out.println("searchRecipeByNick: " + json);
   */ 
    return json;
  }
  
  @RequestMapping(value="searchRecipeByTitle", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String searchRecipeByTitle(
      @RequestParam(defaultValue="0") int pageNo, 
      @RequestParam(defaultValue="4") int pageSize, HttpSession session, String title) throws ServletException, IOException {    
    
    @SuppressWarnings("unused")
    Member member = (Member)session.getAttribute("loginUser");
    
    if (pageSize < 4) { // 최소 3개
      pageSize = 4; 
    } else if (pageSize > 50) { // 최대 50개 
      pageSize = 50;
    }
    List<SearchedRecipe> recipe = recipeService.searchByTitle(title, pageNo, pageSize);
    for(SearchedRecipe r : recipe) {
      r.setLikes(likeService.countAll(r.getBno())); //좋아요를 넣어준다.
    }
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("list", recipe);
    System.out.println(new Gson().toJson(paramMap));
    
/*    if(new Gson().toJson(paramMap).length() > 11) {    
      String json = (new Gson().toJson(paramMap)).split("\\[")[1].split("\\]")[0];
      String[] jsons = json.split("\\{");
      String[] mnos = new String[jsons.length];
      String[] tags = new String[jsons.length];
      String newJson = "{\"list\":[{" + jsons[1].split("\"\\},")[0];
      
      for (int i = 1; i < jsons.length; i++) {      
        mnos[i] = jsons[i].split(":")[1].split(",")[0];     
        tags[i] = jsons[i].split("\"tname\":\"")[1].split("\"")[0];
        
        if (i > 1 && mnos[i].equals(mnos[i-1])) {
          newJson = newJson + " " + tags[i];
        } else if (i != 1 && i != jsons.length - 1) {
          newJson = newJson + "\"},{" + jsons[i].split("\"\\},")[0];
        }
      }
      newJson = newJson + "\"}]}";
      
      return newJson;
    } else {
      return new Gson().toJson(paramMap);
    }    */
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="searchRecipeByTag", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String searchRecipeByTag(
      @RequestParam(defaultValue="0") int pageNo, 
      @RequestParam(defaultValue="4") int pageSize, HttpSession session, String tag) throws ServletException, IOException {    
    
    @SuppressWarnings("unused")
    Member member = (Member)session.getAttribute("loginUser");
    
    if (pageSize < 4) { // 최소 3개
      pageSize = 4; 
    } else if (pageSize > 50) { // 최대 50개 
      pageSize = 50;
    }
    
    List<SearchedRecipe> recipe = recipeService.searchByTag(tag, pageNo, pageSize);
    for(SearchedRecipe r : recipe) {
      r.setLikes(likeService.countAll(r.getBno())); //좋아요를 넣어준다.
    }
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("list", recipe);
    
/*    if(new Gson().toJson(paramMap).length() > 11) {
      String json = (new Gson().toJson(paramMap)).split("\\[")[1].split("\\]")[0];
      String[] jsons = json.split("\\{");
      String[] mnos = new String[jsons.length];
      String[] tags = new String[jsons.length];
      
      System.out.println(new Gson().toJson(paramMap));
      
      String newJson = "{\"list\":[{" + jsons[1].split("\"\\},")[0];
      
      for (int i = 1; i < jsons.length; i++) {      
        mnos[i] = jsons[i].split(":")[1].split(",")[0];     
        tags[i] = jsons[i].split("\"tname\":\"")[1].split("\"")[0];
        
        System.out.printf("jsons[%d]: %s \n", i, jsons[i]); 
        System.out.printf("mnos[%d]: %s // tags[%d]: %s \n", i, mnos[i], i, tags[i]);
        
        if (i > 1 && mnos[i].equals(mnos[i-1])) {
          newJson = newJson + " " + tags[i];
        } else if (i != 1 && i != jsons.length - 1) {
          newJson = newJson + "\"},{" + jsons[i].split("\"\\},")[0];
        }
        
        System.out.printf("newJson[%d] : %s\n", i, newJson);
      }
      newJson = newJson + "\"}]}";
      return newJson;
    } else {
      return new Gson().toJson(paramMap);
    }
    */
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="countRecipeControl", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String countRecipeControl(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");        
    
    //List<RecipeMember> countOfRecipe = countOfRecipe.set(index, element);
    int countOfRecipe = recipeService.countyRecipeControl(member.getNo());
    
    HashMap<String, Object> paramMap = new HashMap<>();
    
    paramMap.put("count", countOfRecipe);
    return new Gson().toJson(paramMap);
  }
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list(int bno) throws ServletException, IOException {
    Recipe recipe = recipeService.list(bno);
    return new Gson().toJson(recipe);
  }
  
  @RequestMapping(value="selectAllTag", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String selectAllTag() throws ServletException, IOException {
    List<Tag> tag = recipeService.selectAllTag();
    System.out.println(new Gson().toJson(tag));
    return new Gson().toJson(tag);
  }
  @RequestMapping(value="addTag", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String addTag(String tname) throws ServletException, IOException {
    Tag tag = new Tag();
    tag.setTname(tname);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      tagService.add(tag);
      result.put("status", "success");
      System.out.println(" 태그성공");
    } catch(Exception e) {
      result.put("status", "failure");
      System.out.println("실패ㅋㅋ");
    }
    
    return new Gson().toJson(result);
  }
  @RequestMapping(value="selectUsingTag", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String selectUsingTag(int bno) throws ServletException, IOException {
    List<Tag> tag = tagService.list(bno);
    return new Gson().toJson(tag);
  }
  
  @RequestMapping(value="addFood", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String addFood(int tno) throws ServletException, IOException {
    Food food = new Food();
    food.setTno(tno);
    System.out.println(tno);
    
    
    HashMap<String,Object> result = new HashMap<>();
    
    try {
      foodService.add(food);
      result.put("status", "success");
      System.out.println(" 음식성공ㅋㅋ");
    } catch(Exception e) {
      result.put("status", "failure");
      System.out.println("실패ㅋㅋ");
    }
    
    return new Gson().toJson(result);
  }
  
  
  @RequestMapping(value="update",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update(int bno,String m_photo, String title, int c_nara, String c_situ, int c_cook, int c_food, int time, int level) throws ServletException, IOException {
    
    Recipe recipe = new Recipe();
    if (m_photo.startsWith("data:image")) {
      String extension = "." + m_photo.split(";")[0].split("/")[1];

      String photoData = m_photo.split(",")[1];

      byte[] data = Base64.decodeBase64(photoData);

      String filename = "";
      filename = System.currentTimeMillis() + extension;

      String realPath = servletContext.getRealPath("files/");
      try (OutputStream stream = new FileOutputStream(realPath + filename)) {
        stream.write(data);
        Thumbnails.of(realPath + filename).crop(Positions.TOP_CENTER).size(700, 500).toFile(new File(realPath+"/thumb/"+filename));
        stream.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      recipe.setM_photo("../files/thumb/" + filename);

    } else {
      recipe.setM_photo(m_photo);
    }
    recipe.setBno(bno);
    recipe.setTitle(title);
    recipe.setC_nara(c_nara);
    recipe.setC_situ(c_situ);
    recipe.setC_cook(c_cook);
    recipe.setC_food(c_food);
    recipe.setTime(time);
    recipe.setLevel(level);
    /*
     
         recipe.setMno(member.getNo());
    recipe.setTitle(title);
    recipe.setM_photo("../files/"+filename);
    recipe.setC_nara(c_nara);
    recipe.setC_situ(c_situ);
    recipe.setC_cook(c_cook);
    recipe.setC_food(c_food);
    recipe.setTime(time);
    recipe.setLevel(level);
     */
    HashMap<String,Object> result = new HashMap<>();
    try {
      recipeService.change(recipe);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
}




