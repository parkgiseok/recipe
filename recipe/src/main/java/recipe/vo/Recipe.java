package recipe.vo;

import java.sql.Date;

public class Recipe {
  int bno;
  int mno;
  String title;
  Date w_dt;
  int count;
  String m_photo;
  int c_nara;
  String c_situ;
  int c_cook;
  int c_food;
  int time;
  int level;
  public Recipe(int bno, int mno, String title, Date w_dt, int count, String m_photo, int c_nara, String c_situ,
      int c_cook, int c_food, int time, int level) {
    super();
    this.bno = bno;
    this.mno = mno;
    this.title = title;
    this.w_dt = w_dt;
    this.count = count;
    this.m_photo = m_photo;
    this.c_nara = c_nara;
    this.c_situ = c_situ;
    this.c_cook = c_cook;
    this.c_food = c_food;
    this.time = time;
    this.level = level;
  }
  public Recipe() {
    super();
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
  public Date getW_dt() {
    return w_dt;
  }
  public void setW_dt(Date w_dt) {
    this.w_dt = w_dt;
  }
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public String getM_photo() {
    return m_photo;
  }
  public void setM_photo(String m_photo) {
    this.m_photo = m_photo;
  }
  public int getC_nara() {
    return c_nara;
  }
  public void setC_nara(int c_nara) {
    this.c_nara = c_nara;
  }
  public String getC_situ() {
    return c_situ;
  }
  public void setC_situ(String c_situ) {
    this.c_situ = c_situ;
  }
  public int getC_cook() {
    return c_cook;
  }
  public void setC_cook(int c_cook) {
    this.c_cook = c_cook;
  }
  public int getC_food() {
    return c_food;
  }
  public void setC_food(int c_food) {
    this.c_food = c_food;
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
  
  
}