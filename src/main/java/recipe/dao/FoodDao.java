package recipe.dao;

import recipe.vo.Food;

public interface FoodDao {
  int insert(Food food);
  int delete(int bno);
  int update(Food food);
}

