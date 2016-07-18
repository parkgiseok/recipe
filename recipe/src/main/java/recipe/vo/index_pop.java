package recipe.vo;

public class index_pop {
	String pop_photo;
	String pop_content;	
	
	public index_pop() {}

  public index_pop(String pop_photo, String pop_content) {
    this.pop_photo = pop_photo;
    this.pop_content = pop_content;
  }

  public String getPop_photo() {
    return pop_photo;
  }

  public void setPop_photo(String pop_photo) {
    this.pop_photo = pop_photo;
  }

  public String getPop_content() {
    return pop_content;
  }

  public void setPop_content(String pop_content) {
    this.pop_content = pop_content;
  }

	
}
