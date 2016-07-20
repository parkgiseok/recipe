package recipe.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.dao.RecipeDao;
import recipe.service.RecipeService;
import recipe.vo.Recipe;
import recipe.vo.RecipeMember;
import recipe.vo.SearchedRecipe;
import recipe.vo.Tag;

@Service
public class DefaultRecipeService implements RecipeService {
  @Autowired RecipeDao recipeDao;
  
  public void add(Recipe recipe) {
    recipeDao.insert(recipe);
  }
  
  public void delete(int bno) {
    recipeDao.deletePhoto(bno);
    recipeDao.deleteRecipe(bno);
  }

  public void change(Recipe recipe) {
    recipeDao.update(recipe);
  }
  
  public Recipe retrieve(Integer bno) {
    return recipeDao.selectOne(bno);
  }
  
  public Recipe list(int bno) {
    return recipeDao.selectList(bno);
  }
  

  public int countPage(int pageSize, int mno) {
    int count = recipeDao.countRecipeControl(mno);
    int pages = count / pageSize;
    if ((count % pageSize) > 0)
      pages++;
    return pages;
  }

  public List<RecipeMember> retrieveRecent(Integer mno) {
    return recipeDao.selectRecentList(mno);
  }
  
  public List<RecipeMember> retrieveRecipeControl(int mno, int si, int len) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("mno",mno);
    paramMap.put("startIndex", (si - 1) * len);
    paramMap.put("length",len);
    return recipeDao.selectRecipeControl(paramMap);
  }

  @Override
  public int countyRecipeControl(int mno) {
    return recipeDao.countRecipeControl(mno);
  }

  @Override
  public void county(int bno) {
    recipeDao.count(bno);    
  }

  @Override
  public List<Recipe> retrieveListByMno(int bno) {
    return recipeDao.selectRecipeByMno(bno);
  }

  @Override
  public List<Tag> selectAllTag() {
    return recipeDao.selectAllTag();
  }
  
  @Override
  public List<Tag> selectTagList(int bno) {
    return recipeDao.selectTagList(bno);
  }
  
  @Override
  public List<SearchedRecipe> searchByTag(String tag, int si, int len) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("tname", tag);
    paramMap.put("startIndex",si);
    paramMap.put("length",len);
    return recipeDao.searchRecipeListByTag(paramMap);
  }
  
  @Override
  public List<SearchedRecipe> searchByTitle(String title, int si, int len) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("title", title);
    paramMap.put("startIndex",si);
    paramMap.put("length",len);
    return recipeDao.searchRecipeListByTitle(paramMap);
  }
  
  @Override
  public List<SearchedRecipe> searchByNick(String nick, int si, int len) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("nick", "%" + nick + "%");
    paramMap.put("startIndex",si);
    paramMap.put("length",len);
    return recipeDao.searchRecipeListByNick(paramMap);
  }
  
}

/*
# Service 객체
- 비즈니스 로직을 수행한다.
- 트랜잭션을 제어한다.
- 메서드의 이름은 업무 용어에 가깝게 정의하라!
- 업무 처리에 필요하다면 여러 개의 DAO를 사용할 수 있다.
*/
