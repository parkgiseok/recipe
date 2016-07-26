package recipe.service;

import java.util.List;

import recipe.vo.Member;

public interface MemberService {
  void add(Member member);  
  void delete(int no);  
  void change(Member member);  
  void logoutchange(Member member);  
  Member retrieve(int no);  
  Member retrieveByNo(int no);
  Member retrieveById(String id);
  List<Member> list(int pageNo, int pageSize);
  boolean exist(String id, String password);
  int countPage(int pageSize);  
}
