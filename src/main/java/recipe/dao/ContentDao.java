package recipe.dao;

import java.util.List;

import recipe.vo.Content;

public interface ContentDao {
  List<Content> selectOne(int pno);
  
  List<Content> selectList(int bno);

  int insert(Content content);
  
  int updateinsert(Content content);

  int delete(int bno);

  int update(Content content);
}
