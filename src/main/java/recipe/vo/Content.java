package recipe.vo;

public class Content {
  int pno;
  int bno;
  String cont;
  String p_url;

  public Content(int pno, int bno, String cont, String p_url) {
    this.pno = pno;
    this.bno = bno;
    this.cont = cont;
    this.p_url = p_url;
  }

  
  public Content() {
    super();
  }


  public int getPno() {
    return pno;
  }
  public void setPno(int pno) {
    this.pno = pno;
  }
  public int getBno() {
    return bno;
  }
  public void setBno(int bno) {
    this.bno = bno;
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
  
  
}