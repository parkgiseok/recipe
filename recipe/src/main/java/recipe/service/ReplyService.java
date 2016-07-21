package recipe.service;

import java.util.List;

import recipe.vo.Reply;
import recipe.vo.ReplyData;
import recipe.vo.ReplyRecipeMember;

public interface ReplyService {
  void add(Reply userreview);
  void delete(int mno,int bno);
  void change(Reply userreview);
  List<ReplyData> list(int bno, int page);
  Reply retrieveByNo(int mno, int bno);
  int countPage(int pageSize, int mno);
  List<ReplyRecipeMember> replyRecipeRecent(int no, int pageNo, int pageSize);
  int county(int no);
}