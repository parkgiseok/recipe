package recipe.dao;

import java.util.List;
import java.util.Map;

import recipe.vo.Favorite;
import recipe.vo.RecipeMember;

public interface FavoriteDao {

  Favorite selectOne(Map<String, Object> paramMap);

  int insert(Favorite favorite);

  int delete(Map<String, Object> paramMap);

  int countAll(int bno);
  
  int isFavorite(Map<String, Object> paramMap);
  
  List<RecipeMember> favoriteRecipe(Map<String, Object> paramMap);
  
  int countRecipeFavorite(int mno);
  
}
