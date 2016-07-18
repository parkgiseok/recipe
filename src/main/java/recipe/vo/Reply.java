package recipe.vo;

import java.sql.Date;

public class Reply {

  int rno;
  int bno;
  int mno;
  String r_cont;
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

  public String getR_cont() {
    return r_cont;
  }

  public void setR_cont(String r_cont) {
    this.r_cont = r_cont;
  }

  public Date getR_w_dt() {
    return r_w_dt;
  }

  public void setR_w_dt(Date r_w_dt) {
    this.r_w_dt = r_w_dt;
  }

  @Override
  public String toString() {
    return "Reply [rno=" + rno + ", bno=" + bno + ", mno=" + mno + ", r_cont=" + r_cont + ", r_w_dt=" + r_w_dt + "]";
  }

}