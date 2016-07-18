package recipe.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.dao.LikesDao;
import recipe.service.LikesService;
import recipe.vo.Likes;

@Service
public class DefaultLikesService implements LikesService {

  @Autowired
  LikesDao likesDao;

  public void add(Likes likes) {
    likesDao.insert(likes);
  }

  public void delete(int mno,int bno) {
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("mno", mno);
    paramMap.put("bno", bno);
    likesDao.delete(paramMap);
  }

  public Likes retrieveByNO(int mno,int bno) {
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("mno", mno);
    paramMap.put("bno", bno);
    return likesDao.selectOne(paramMap);
  }

  @Override
  public int countAll(int bno) {
    return likesDao.countAll(bno);
  }

  @Override
  public boolean isLike(int bno, int mno) {
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("bno", bno);
    paramMap.put("mno", mno);
    if(likesDao.isLike(paramMap) > 0) {
      return true;
    } else {
      return false;
    }
  }

}
/*
 * # Service 객체 - 비즈니스 로직을 수행한다. - 트랜잭션을 제어한다 - 메서드의 이름은 업무 용어에 가깝게 정의하라 - 업무
 * 처리에 필요하다면, 여러 개의 DAO 를 사용할 수 있다.
 */
