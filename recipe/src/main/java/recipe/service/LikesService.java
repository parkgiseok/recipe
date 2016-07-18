package recipe.service;

import recipe.vo.Likes;

public interface LikesService {
  void add(Likes likes);
  void delete(int mno,int bno);
  Likes retrieveByNO(int mno,int bno);
  int countAll(int bno);
  boolean isLike(int bno, int mno);
}
