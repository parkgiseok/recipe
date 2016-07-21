package recipe.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.dao.ReplyDao;
import recipe.service.ReplyService;
import recipe.vo.Reply;
import recipe.vo.ReplyData;
import recipe.vo.ReplyRecipeMember;

@Service
public class DefaultReplyService implements ReplyService {

  @Autowired
  ReplyDao replyDao;

  public void add(Reply userreview) {
    replyDao.insert(userreview);
  }

  @Override
  public void delete(int mno, int bno) {
    // TODO Auto-generated method stub
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("bno", bno);
    paramMap.put("mno", mno);
    replyDao.delete(paramMap);
  }

  @Override
  public void change(Reply userreview) {
    replyDao.update(userreview);
  }

  @Override
  public List<ReplyData> list(int bno,int page) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("bno", bno);
    paramMap.put("start", (page - 1) * 6 );
    paramMap.put("end", 6);
    
    return replyDao.selectList(paramMap);
  }

  @Override
  public Reply retrieveByNo(int mno, int bno) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("bno", bno);
    paramMap.put("mno", mno);
    return replyDao.selectOne(paramMap);
  }

  @Override
  public int countPage(int pageSize, int mno) {
    int count = replyDao.countRecipeReply(mno);
    int pages = count / pageSize;
    if ((count % pageSize) > 0)
      pages++;
    return pages;
  }

  public List<ReplyRecipeMember> replyRecipeRecent(int no, int pageNo, int pageSize) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("mno",no);
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length",pageSize);
    return replyDao.selectRecentList(paramMap);
  }

  @Override
  public int county(int no) {
    return replyDao.countRecipeReply(no);
  }

}
