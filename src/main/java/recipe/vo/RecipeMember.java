package recipe.vo;

import java.sql.Date;

public class RecipeMember {
  private int bno;
  private Integer mno;
  private String m_photo;
  private String title;
  private Date w_dt;
  private String cont;

  public int getBno() {
    return bno;
  }
  public void setBno(int bno) {
    this.bno = bno;
  }
  public Integer getMno() {
    return mno;
  }
  public void setMno(Integer mno) {
    this.mno = mno;
  }
  public String getM_photo() {
    return m_photo;
  }
  public void setM_photo(String m_photo) {
    this.m_photo = m_photo;
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
  public String getCont() {
    return cont;
  }
  public void setCont(String cont) {
    this.cont = cont;
  }

}
