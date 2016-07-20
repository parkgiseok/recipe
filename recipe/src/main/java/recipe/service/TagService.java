package recipe.service;

import java.util.List;

import recipe.vo.Tag;

public interface TagService {
  void add(Tag tag);  
  List<Tag> list(int bno);

}
