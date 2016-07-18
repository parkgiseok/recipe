package recipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.dao.ContentDao;
import recipe.service.ContentService;
import recipe.vo.Content;

@Service
public class DefaultContentService implements ContentService {
  @Autowired
  ContentDao contentDao;

  public void add(Content content) {
    contentDao.insert(content);
  }
  public void updateadd(Content content) {
    contentDao.updateinsert(content);
  }

  public void delete(int bno) {
    contentDao.delete(bno);
  }

  public void change(Content content) {
    contentDao.update(content);
  }

  public List<Content> retrieve(int pno) {
    return contentDao.selectOne(pno);
  }

  public List<Content> list(int bno) {
    return contentDao.selectList(bno);
  }
}

/*
 * # Service 객체 - 비즈니스 로직을 수행한다. - 트랜잭션을 제어한다. - 메서드의 이름은 업무 용어에 가깝게 정의하라! - 업무
 * 처리에 필요하다면 여러 개의 DAO를 사용할 수 있다.
 */
