package recipe.vo;

public class Index {
  int bno;
  int mno;
  String title;
  String m_photo;
  String nick;
  String photo;
  int time;
  int level;
  int likes;

  
  @Override
  public String toString() {
    return "Index [bno=" + bno + ", mno=" + mno + ", title=" + title + ", m_photo=" + m_photo + ", nick=" + nick
        + ", photo=" + photo + ", time=" + time + ", level=" + level + ", likes=" + likes + "]";
  }
  
  public int getBno() {
    return bno;
  }
  public void setBno(int bno) {
    this.bno = bno;
  }
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getM_photo() {
    return m_photo;
  }
  public void setM_photo(String m_photo) {
    this.m_photo = m_photo;
  }
  public String getNick() {
    return nick;
  }
  public void setNick(String nick) {
    this.nick = nick;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public int getTime() {
    return time;
  }
  public void setTime(int time) {
    this.time = time;
  }
  public int getLevel() {
    return level;
  }
  public void setLevel(int level) {
    this.level = level;
  }
  public int getLikes() {
    return likes;
  }
  public void setLikes(int likes) {
    this.likes = likes;
  }
  
  
}