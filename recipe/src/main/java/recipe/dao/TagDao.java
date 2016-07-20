package recipe.dao;

import java.util.List;

import recipe.vo.Tag;

public interface TagDao {
  int insert(Tag tag);
  List<Tag> selectList(int bno);
  
}

