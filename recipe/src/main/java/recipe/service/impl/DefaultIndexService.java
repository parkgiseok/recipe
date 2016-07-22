package recipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.dao.IndexDao;
import recipe.service.IndexService;
import recipe.vo.Index;
import recipe.vo.MainRecipe;

@Service
public class DefaultIndexService implements IndexService {
  @Autowired IndexDao indexDao;
  
  public List<MainRecipe> retrieveIndex() {
    return indexDao.MainRecipeList();
  }
  public List<Index> retrieveRankingIndex(int mno) {
    return indexDao.selectIndexRankingList(mno);
  }
/*
  @Override
  public List<MainRecipe> retrieveCountIndex() {
    return indexDao.MainRecipeCountList();
  }
*/
}

/*
# Service 객체
- 비즈니스 로직을 수행한다.
- 트랜잭션을 제어한다.
- 메서드의 이름은 업무 용어에 가깝게 정의하라!
- 업무 처리에 필요하다면 여러 개의 DAO를 사용할 수 있다.
*/
