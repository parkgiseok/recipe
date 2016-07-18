package recipe.vo;

import java.sql.Date;

public class ReplyRecipeMember {
  int rno;
  int bno;
  int mno;
  String m_photo;
  String title;
  String cont;
  String r_cont;
  Date w_dt;
  Date r_w_dt;

  public int getRno() {
    return rno;
  }

  public void setRno(int rno) {
    this.rno = rno;
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

  public String getCont() {
    return cont;
  }

  public void setCont(String cont) {
    this.cont = cont;
  }

  public String getR_cont() {
    return r_cont;
  }

  public void setR_cont(String r_cont) {
    this.r_cont = r_cont;
  }

  public Date getW_dt() {
    return w_dt;
  }

  public void setW_dt(Date w_dt) {
    this.w_dt = w_dt;
  }

  public Date getR_w_dt() {
    return r_w_dt;
  }

  public void setR_w_dt(Date r_w_dt) {
    this.r_w_dt = r_w_dt;
  }

  @Override
  public String toString() {
    return "ReplyRecipeMember [rno=" + rno + ", bno=" + bno + ", mno=" + mno + ", m_photo=" + m_photo + ", title="
        + title + ", cont=" + cont + ", r_cont=" + r_cont + ", w_dt=" + w_dt + ", r_w_dt=" + r_w_dt + "]";
  }

}