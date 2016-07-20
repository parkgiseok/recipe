package recipe.vo;

import java.util.Date;

public class MainRecipe {
  int bno;
  int mno;
  String m_photo;
  String photo;
  String nick;
  String title;
  int time;
  int likes;
  Date w_dt;
  int c_food;
  String c_situ;
  int c_nara;
  int c_cook;
  int level;
  int pno;
  String cont;
  String p_url;
  String tname;
  

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


  public String getM_photo() {
    return m_photo;
  }


  public void setM_photo(String m_photo) {
    this.m_photo = m_photo;
  }


  public String getPhoto() {
    return photo;
  }


  public void setPhoto(String photo) {
    this.photo = photo;
  }


  public String getNick() {
    return nick;
  }


  public void setNick(String nick) {
    this.nick = nick;
  }


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public int getTime() {
    return time;
  }


  public void setTime(int time) {
    this.time = time;
  }


  public int getLikes() {
    return likes;
  }


  public void setLikes(int likes) {
    this.likes = likes;
  }


  public Date getW_dt() {
    return w_dt;
  }


  public void setW_dt(Date w_dt) {
    this.w_dt = w_dt;
  }


  public int getC_food() {
    return c_food;
  }


  public void setC_food(int c_food) {
    this.c_food = c_food;
  }


  public String getC_situ() {
    return c_situ;
  }


  public void setC_situ(String c_situ) {
    this.c_situ = c_situ;
  }


  public int getC_nara() {
    return c_nara;
  }


  public void setC_nara(int c_nara) {
    this.c_nara = c_nara;
  }


  public int getC_cook() {
    return c_cook;
  }


  public void setC_cook(int c_cook) {
    this.c_cook = c_cook;
  }


  public int getLevel() {
    return level;
  }


  public void setLevel(int level) {
    this.level = level;
  }


  public int getPno() {
    return pno;
  }


  public void setPno(int pno) {
    this.pno = pno;
  }


  public String getCont() {
    return cont;
  }


  public void setCont(String cont) {
    this.cont = cont;
  }


  public String getP_url() {
    return p_url;
  }


  public void setP_url(String p_url) {
    this.p_url = p_url;
  }
  
  public String getTname() {
    return tname;
  }


  public void setTname(String tname) {
    this.tname = tname;
  }

  @Override
  public String toString() {
    return "MainRecipe [bno=" + bno + ", mno=" + mno + ", m_photo=" + m_photo + ", photo=" + photo + ", nick=" + nick
        + ", title=" + title + ", time=" + time + ", likes=" + likes + ", w_dt=" + w_dt + ", c_food=" + c_food
        + ", c_situ=" + c_situ + ", c_nara=" + c_nara + ", c_cook=" + c_cook + ", level=" + level + ", pno=" + pno
        + ", cont=" + cont + ", p_url=" + p_url + ", tname=" + tname + "]";
  }
}