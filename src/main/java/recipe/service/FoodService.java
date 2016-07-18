package recipe.service;

import recipe.vo.Food;

public interface FoodService {
  void add(Food food);  
  void delete(int bno);  
  void change(Food food);  
}
