package recipe.service;

import java.util.List;

import recipe.vo.Content;

public interface ContentService {
  void add(Content content);
  
  void updateadd(Content content);

  void delete(int bno);

  void change(Content content);

  List<Content> retrieve(int pno);
  
  List<Content> list(int bno);
}
