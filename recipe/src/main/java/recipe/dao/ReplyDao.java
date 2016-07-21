package recipe.dao;

import java.util.List;
import java.util.Map;

import recipe.vo.Reply;
import recipe.vo.ReplyData;
import recipe.vo.ReplyRecipeMember;

public interface ReplyDao {
  List<ReplyData> selectList(Map<String,Object> paramMap);
  List<ReplyRecipeMember> selectRecentList(Map<String,Object> paramMap);
  void insert(Reply userreview);
  void delete(Map<String,Object> paramMap);
  void update(Reply userreview);
  Reply selectOne(Map<String, Object> paramMap);
  int countAll();
  int countRecipeReply(int mno);
}