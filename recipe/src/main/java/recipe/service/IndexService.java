package recipe.service;

import java.util.List;

import recipe.vo.Index;
import recipe.vo.MainRecipe;

public interface IndexService {
  //List<Index> retrieveIndex();
  List<MainRecipe> retrieveIndex();
  //List<MainRecipe> retrieveCountIndex();
  List<Index> retrieveRankingIndex(int mno);
}
