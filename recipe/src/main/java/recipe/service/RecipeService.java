package recipe.service;

import java.util.List;

import recipe.vo.Recipe;
import recipe.vo.RecipeMember;
import recipe.vo.SearchedRecipe;
import recipe.vo.Tag;

public interface RecipeService {
  void add(Recipe recipe);  
  void delete(int bno);  
  void change(Recipe recipe);  
  Recipe retrieve(Integer bno);  
  Recipe list(int bno);
  int countPage(int pageSize, int mno);
  List<RecipeMember> retrieveRecent(Integer mno);  
  List<RecipeMember> retrieveRecipeControl(int mno, int si, int len);
  List<Tag> selectAllTag();
  List<Tag> selectTagList(int bno);
  int countyRecipeControl(int mno);
  void county(int bno);
  List<Recipe> retrieveListByMno(int mno);
  List<SearchedRecipe> searchByNick(String nick, int si, int len); 
  List<SearchedRecipe> searchByTitle(String title, int si, int len); 
  List<SearchedRecipe> searchByTag(String tname, int si, int len);
}
