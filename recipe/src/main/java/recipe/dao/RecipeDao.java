package recipe.dao;

import java.util.List;
import java.util.Map;

import recipe.vo.Recipe;
import recipe.vo.RecipeMember;
import recipe.vo.SearchedRecipe;
import recipe.vo.Tag;

public interface RecipeDao {
  Recipe selectList(int bno);
  Recipe selectOne(Integer bno);
  int insert(Recipe recipe);
  Integer deletePhoto(int bno);
  Integer deleteRecipe(int bno);
  int update(Recipe recipe);
  int countAll();
  List<RecipeMember> selectRecentList(Integer mno);
  List<RecipeMember> selectRecipeControl(Map<String, Object> paramMap);
  List<Tag> selectAllTag();
  int countRecipeControl(int mno);
  void count(int bno);
  List<Recipe> selectRecipeByMno(Map<String, Object> paramMap);
  List<SearchedRecipe> searchRecipeListByNick(Map<String, Object> paramMap);
  List<SearchedRecipe> searchRecipeListByTitle(Map<String, Object> paramMap);
  List<SearchedRecipe> searchRecipeListByTag(Map<String, Object> paramMap);
  List<Tag> selectTagList(int bno);
}

