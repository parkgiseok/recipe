package recipe.dao;

import java.util.List;
import java.util.Map;

import recipe.vo.Member;

public interface MemberDao {
  List<Member> selectList(Map<String,Object> paramMap);
  Member selectOne(int no);
  Member SelectOne(Map<String,Object> paramMap);
  int insert(Member member);
  int delete(int no);
  int update(Member member);
  int logoutupdate(Member member);
  int isMember(Map<String,Object> paramMap);
  int countAll();
}
