package recipe.service;

import java.util.List;

import recipe.vo.Favorite;
import recipe.vo.RecipeMember;

public interface FavoriteService {
  void add(Favorite favorite);
  void delete(int mno,int bno);
  Favorite retrieveByNO(int mno,int bno);
  int countAll(int bno);
  boolean isFavorite(int bno, int mno);
  List<RecipeMember> retrieveRecent(Integer mno);  
}
