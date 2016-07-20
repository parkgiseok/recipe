package recipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.dao.TagDao;
import recipe.service.TagService;
import recipe.vo.Tag;

@Service
public class DefaultTagService implements TagService {
  @Autowired TagDao tagDao;
  
  public void add(Tag tag) {
    tagDao.insert(tag);
  }
  public List<Tag> list(int bno) {
    return tagDao.selectList(bno);
  }
}

/*
# Service 객체
- 비즈니스 로직을 수행한다.
- 트랜잭션을 제어한다.
- 메서드의 이름은 업무 용어에 가깝게 정의하라!
- 업무 처리에 필요하다면 여러 개의 DAO를 사용할 수 있다.
*/
