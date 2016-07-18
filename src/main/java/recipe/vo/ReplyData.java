package recipe.vo;

import java.sql.Date;

public class ReplyData {
  int mno;
  String nick;
  String photo;
  String r_cont;
  Date r_w_dt;

  public int getMno() {
    return mno;
  }

  public void setMno(int mno) {
    this.mno = mno;
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
    return "ReplyData [mno=" + mno + ", nick=" + nick + ", photo=" + photo + ", r_cont=" + r_cont + ", r_w_dt=" + r_w_dt
        + "]";
  }

}