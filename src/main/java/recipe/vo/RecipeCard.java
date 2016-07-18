package recipe.vo;

import java.util.Date;

public class RecipeCard {
	String nickname;
	String profilePhoto;	
	String mainPhoto;
	String title;
	Date writeDate;
	int time;
	int like;
	int level;
	String description;	
	String pop_photo;
	String pop_content;
	
	public RecipeCard() {}

  public RecipeCard(String nickname, String profilePhoto, String mainPhoto, String title, int time,
      int like, int level, String description) {
    this.nickname = nickname;
    this.profilePhoto = profilePhoto;
    this.mainPhoto = mainPhoto;
    this.title = title;
    this.time = time;
    this.like = like;
    this.level = level;
    this.description = description;
  }
  
  public RecipeCard(String pop_photo, String pop_content) {
    this.pop_photo = pop_photo;
    this.pop_content = pop_content;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getProfilePhoto() {
    return profilePhoto;
  }

  public void setProfilePhoto(String profilePhoto) {
    this.profilePhoto = profilePhoto;
  }

  public String getMainPhoto() {
    return mainPhoto;
  }

  public void setMainPhoto(String mainPhoto) {
    this.mainPhoto = mainPhoto;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getWriteDate() {
    return writeDate;
  }

  public void setWriteDate(Date writeDate) {
    this.writeDate = writeDate;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public int getLike() {
    return like;
  }

  public void setLike(int like) {
    this.like = like;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
