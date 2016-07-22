package recipe.dao;

import java.util.List;

import recipe.vo.Index;
import recipe.vo.MainRecipe;

public interface IndexDao {
  List<MainRecipe> MainRecipeList();
  //List<MainRecipe> MainRecipeCountList();
  List<Index> selectIndexRankingList(int mno);
}

