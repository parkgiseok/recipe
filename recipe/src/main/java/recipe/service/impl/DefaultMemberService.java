package recipe.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.dao.MemberDao;
import recipe.service.MemberService;
import recipe.vo.Member;

@Service
public class DefaultMemberService implements MemberService {
  @Autowired MemberDao memberDao;
  
  public void add(Member member) {
	  memberDao.insert(member);
  }
  
  public void delete(int no) {
	  memberDao.delete(no);
  }
  
  public Member retrieve(int no) {
    return memberDao.selectOne(no);
  }
  
  public Member retrieveByNo(int no) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    
    return memberDao.SelectOne(paramMap);
  }
  
  public Member retrieveById(String id) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    
    return memberDao.SelectOne(paramMap);
  }
  
  public boolean exist(String id, String password) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    paramMap.put("password", password);
    
    if (memberDao.isMember(paramMap) > 0) {
      return true;
    }
    
    return false;
  }
  
  public List<Member> list(int pageNo, int pageSize) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    
    return memberDao.selectList(paramMap);
  }
  
  public void change(Member member) {
	  memberDao.update(member);
  }

  public void logoutchange(Member member) {
    memberDao.logoutupdate(member);
  }

  public int countPage(int pageSize) {
    int count = memberDao.countAll();
    int pages = count / pageSize;
    if ((count % pageSize) > 0)
      pages++;
    return pages;
  }
}

/*
# Service 객체
- 비즈니스 로직을 수행한다.
- 트랜잭션을 제어한다.
- 메서드의 이름은 업무 용어에 가깝게 정의하라!
- 업무 처리에 필요하다면 여러 개의 DAO를 사용할 수 있다.
*/