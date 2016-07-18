package recipe.dao;

import java.util.List;

import recipe.vo.Index;
import recipe.vo.MainRecipe;

public interface IndexDao {
  List<MainRecipe> MainRecipeList();
  List<Index> selectIndexRankingList(int mno);
}

