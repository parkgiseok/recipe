package recipe.vo;

import java.sql.Date;

public class SearchedRecipe {
  private int mno;
  int likes;
  private String nick;
  private String photo;
  private String desct;
  private String category;
  private String title;
  private int bno;  
  private Date w_dt;
  private String m_photo;
  private String time;
  private String level;
  private String cont;
  private String tname;
  private String c_situ;
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public int getLikes() {
    return likes;
  }
  public void setLikes(int likes) {
    this.likes = likes;
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
  public String getDesct() {
    return desct;
  }
  public void setDesct(String desct) {
    this.desct = desct;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public int getBno() {
    return bno;
  }
  public void setBno(int bno) {
    this.bno = bno;
  }
  public Date getW_dt() {
    return w_dt;
  }
  public void setW_dt(Date w_dt) {
    this.w_dt = w_dt;
  }
  public String getM_photo() {
    return m_photo;
  }
  public void setM_photo(String m_photo) {
    this.m_photo = m_photo;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  public String getLevel() {
    return level;
  }
  public void setLevel(String level) {
    this.level = level;
  }
  public String getCont() {
    return cont;
  }
  public void setCont(String cont) {
    this.cont = cont;
  }
  public String getTname() {
    return tname;
  }
  public void setTname(String tname) {
    this.tname = tname;
  }
  public String getC_situ() {
    return c_situ;
  }
  public void setC_situ(String c_situ) {
    this.c_situ = c_situ;
  }
  
  public SearchedRecipe() {
  }
  
    
  
}
