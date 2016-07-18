package recipe.service;

import java.util.List;

import recipe.vo.Index;
import recipe.vo.MainRecipe;

public interface IndexService {
  //List<Index> retrieveIndex();
  List<MainRecipe> retrieveIndex();
  List<Index> retrieveRankingIndex(int mno);
}
