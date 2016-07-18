package recipe.vo;

public class Likes {

  int mno;
  int bno;
  
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
  
  @Override
  public String toString() {
    return "Likes [mno=" + mno + ", bno=" + bno + "]";
  }
  
  
}
