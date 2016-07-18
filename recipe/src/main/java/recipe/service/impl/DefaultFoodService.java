package recipe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.dao.FoodDao;
import recipe.service.FoodService;
import recipe.vo.Food;

@Service
public class DefaultFoodService implements FoodService {
  @Autowired FoodDao foodDao;
  
  public void add(Food food) {
    foodDao.insert(food);
  }
  
  public void delete(int bno) {
    foodDao.delete(bno);
  }

  public void change(Food food) {
    foodDao.update(food);
  }
}

/*
# Service 객체
- 비즈니스 로직을 수행한다.
- 트랜잭션을 제어한다.
- 메서드의 이름은 업무 용어에 가깝게 정의하라!
- 업무 처리에 필요하다면 여러 개의 DAO를 사용할 수 있다.
*/
