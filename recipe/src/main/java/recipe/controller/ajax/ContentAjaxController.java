package recipe.controller.ajax;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import recipe.service.ContentService;
import recipe.vo.Content;

@Controller
@RequestMapping("/ajax/content/")
public class ContentAjaxController {
  @Autowired
  ContentService contentService;
  @Autowired
  ServletContext servletContext;

  @RequestMapping(value = "add", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String add(String p_url, String cont) throws ServletException, IOException {
    Content content = new Content();

    String extension = "." + p_url.split(";")[0].split("/")[1];

    String photoData = p_url.split(",")[1];

    byte[] data = Base64.decodeBase64(photoData);

    String filename = "";
    filename = System.currentTimeMillis() + extension;

    String realPath = servletContext.getRealPath("files/");
    try (OutputStream stream = new FileOutputStream(realPath + filename)) {
      stream.write(data);
      Thumbnails.of(realPath + filename).width(700).height(500).toFile(new File(realPath+"/thumb/"+filename));
      stream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    content.setP_url("../files/thumb/" + filename);
    content.setCont(cont);
    HashMap<String, Object> result = new HashMap<>();
    try {
      contentService.add(content);
      result.put("status", "success");
      System.out.println(" 성공ㅋㅋ");
    } catch (Exception e) {
      result.put("status", "failure");
      e.printStackTrace();
      System.out.println("실패ㅋㅋ");
    }

    return new Gson().toJson(result);
  }
  @RequestMapping(value = "updateadd", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String updateadd(int bno,String p_url, String cont, int q) throws ServletException, IOException {
    Content content = new Content();

    String extension = "." + p_url.split(";")[0].split("/")[1];

    String photoData = p_url.split(",")[1];

    byte[] data = Base64.decodeBase64(photoData);

    String filename = "";
    filename = System.currentTimeMillis() + extension;

    String realPath = servletContext.getRealPath("files/");
    try (OutputStream stream = new FileOutputStream(realPath + filename)) {
      stream.write(data);
      Thumbnails.of(realPath + filename).width(700).height(500).toFile(new File(realPath+"/thumb/"+filename));
      stream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    content.setBno(bno);
    content.setP_url("../files/thumb/" + filename);
    content.setCont(cont);
    HashMap<String, Object> result = new HashMap<>();
    try {
      contentService.updateadd(content);
      result.put("status", "success");
      System.out.println(" 성공ㅋㅋ");
    } catch (Exception e) {
      result.put("status", "failure");
      e.printStackTrace();
      System.out.println("실패ㅋㅋ");
    }

    return new Gson().toJson(result);
  }
  /*
   * @RequestMapping(value="add", method=RequestMethod.POST,
   * produces="application/json;charset=UTF-8")
   * 
   * @ResponseBody public String add(MultipartHttpServletRequest request,String
   * cont) throws ServletException, IOException { Content content = new
   * Content(); System.out.println("request" + request); Map<String,
   * MultipartFile> files = request.getFileMap(); System.out.println(files);
   * CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("uploadFile");
   * System.out.println(cmf); int extPoint =
   * cmf.getOriginalFilename().lastIndexOf("."); System.out.println(
   * "extPoint : " + extPoint);
   * 
   * String filename=""; filename = System.currentTimeMillis() +
   * cmf.getOriginalFilename().substring(extPoint);
   * System.out.printf("새파일명=%s\n", filename); String realPath =
   * servletContext.getRealPath("files/" + filename); System.out.printf(
   * "새 파일을 저장할 실제 경로=%s\n", realPath); try { System.out.println("오호라");
   * content.setPhoto("files/"+filename); content.setCont(cont);
   * cmf.transferTo(new File(realPath)); } catch (Exception e) {
   * e.printStackTrace(); } System.out.println(content.getPhoto());
   * System.out.println(content.getCont()); content.setCont(cont);
   * content.setPhoto(photo);
   * 
   * HashMap<String,Object> result = new HashMap<>(); try {
   * 
   * contentService.add(content); System.out.println("성공"); result.put("status",
   * "success"); } catch(Exception e) { System.out.println(e);
   * result.put("status", "failure"); }
   * 
   * return new Gson().toJson(result); }
   */

  @RequestMapping(value = "delete", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String delete(int bno) throws ServletException, IOException {
    HashMap<String, Object> result = new HashMap<>();
    try {
      contentService.delete(bno);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }

  @RequestMapping(value = "detail", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String detail(int pno) throws ServletException, IOException {
    List<Content> content = contentService.retrieve(pno);
    return new Gson().toJson(content);
  }

  @RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String list(int bno) throws ServletException, IOException {
    List<Content> content = contentService.list(bno);
    return new Gson().toJson(content);
  }

  @RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String update(int pno, String cont, String p_url) throws ServletException, IOException {

    Content content = new Content();
    content.setPno(pno);
    if (p_url.startsWith("data:image")) {
      String extension = "." + p_url.split(";")[0].split("/")[1];

      String photoData = p_url.split(",")[1];

      byte[] data = Base64.decodeBase64(photoData);

      String filename = "";
      filename = System.currentTimeMillis() + extension;

      String realPath = servletContext.getRealPath("files/");
      try (OutputStream stream = new FileOutputStream(realPath + filename)) {
        stream.write(data);
        Thumbnails.of(realPath + filename).width(700).height(500).toFile(new File(realPath+"/thumb/"+filename));
        stream.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      content.setP_url("../files/thumb/" + filename);

    } else {
      content.setP_url(p_url);
    }
    content.setCont(cont);

    HashMap<String, Object> result = new HashMap<>();
    try {
      contentService.change(content);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
}
