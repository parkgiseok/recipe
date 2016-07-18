package recipe.service;

import java.util.List;

import recipe.vo.Recipe;
import recipe.vo.RecipeMember;
import recipe.vo.Tag;

public interface RecipeService {
  void add(Recipe recipe);  
  void delete(int bno);  
  void change(Recipe recipe);  
  Recipe retrieve(Integer bno);  
  Recipe list(int bno);
  int countPage(int pageSize);
  List<RecipeMember> retrieveRecent(Integer mno);  
  List<RecipeMember> retrieveRecipeControl(int mno, int si, int len);
  List<Tag> selectAllTag();
  int countyRecipeControl(int mno);
  void county(int bno);
  List<Recipe> retrieveListByMno(int mno);  
}
