package recipe.dao;

import java.util.Map;

import recipe.vo.Likes;

public interface LikesDao {

  Likes selectOne(Map<String, Object> paramMap);

  int insert(Likes likes);

  int delete(Map<String, Object> paramMap);

  int countAll(int bno);
  
  int isLike(Map<String, Object> paramMap);
}
