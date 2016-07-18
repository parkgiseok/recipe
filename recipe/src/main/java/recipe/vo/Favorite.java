package recipe.vo;

import java.sql.Date;

public class Favorite {
  int fno;
  int mno;
  int bno;
  Date add_dt;
  
  public int getFno() {
    return fno;
  }
  public void setFno(int fno) {
    this.fno = fno;
  }
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public int getBno() {
    return bno;
  }
  public void setBno(int bno) {
    this.bno = bno;
  }
  public Date getAdd_dt() {
    return add_dt;
  }
  public void setAdd_dt(Date add_dt) {
    this.add_dt = add_dt;
  }
  
  @Override
  public String toString() {
    return "Favorite [fno=" + fno + ", mno=" + mno + ", bno=" + bno + ", add_dt=" + add_dt + "]";
  }
  
  
}
