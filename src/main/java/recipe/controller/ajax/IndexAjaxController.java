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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import recipe.service.IndexService;
import recipe.service.LikesService;
import recipe.vo.Index;
import recipe.vo.Member;

@Controller
@RequestMapping("/ajax/index/")
public class IndexAjaxController {
  @Autowired IndexService indexService;
  @Autowired LikesService likeService;


  // 레시피 순위 카드
  @RequestMapping(value="detailRankingIndex", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detailIndex(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    List<Index> index = indexService.retrieveRankingIndex(member.getNo());
    for(Index i : index) {
      i.setLikes(likeService.countAll(i.getBno())); //좋아요를 넣어준다.
    }
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("list", index);

    return new Gson().toJson(paramMap);
  }
}
