package recipe.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.dao.FavoriteDao;
import recipe.service.FavoriteService;
import recipe.vo.Favorite;
import recipe.vo.RecipeMember;

@Service
public class DefaultFavoriteService implements FavoriteService {

  @Autowired
  FavoriteDao favoriteDao;

  public void add(Favorite favorite) {
    favoriteDao.insert(favorite);
  }

  public void delete(int mno,int bno) {
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("mno", mno);
    paramMap.put("bno", bno);
    favoriteDao.delete(paramMap);
  }

  public Favorite retrieveByNO(int mno, int bno) {
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("mno", mno);
    paramMap.put("bno", bno);
    return favoriteDao.selectOne(paramMap);
  }

  @Override
  public int countAll(int bno) {
    return favoriteDao.countAll(bno);
  }

  @Override
  public boolean isFavorite(int bno, int mno) {
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("bno", bno);
    paramMap.put("mno", mno);
    if(favoriteDao.isFavorite(paramMap) > 0) {
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public List<RecipeMember> retrieveRecent(int mno, int pageNo, int pageSize) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("mno",mno);
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length",pageSize);
    return favoriteDao.favoriteRecipe(paramMap);
  }
  
  @Override
  public int countyRecipeFavorite(int mno) {
    return favoriteDao.countRecipeFavorite(mno);
  }

  @Override
  public int countPage(int pageSize, int mno) {
    int count = favoriteDao.countRecipeFavorite(mno);
    int pages = count / pageSize;
    if ((count % pageSize) > 0)
      pages++;
    return pages;
  }

}
/*
 * # Service 객체 - 비즈니스 로직을 수행한다. - 트랜잭션을 제어한다 - 메서드의 이름은 업무 용어에 가깝게 정의하라 - 업무
 * 처리에 필요하다면, 여러 개의 DAO 를 사용할 수 있다.
 */
