package recipe.vo;

import java.sql.Date;

public class Member {
  int no;
  String id;
  String email;
  String password;
  String nick;
  String photo;
  String gender;
  Date startDate;
  Date lastDate;
  String category;
  String description;
  
  public Member() {}

  public Member(int no, String id, String email, String password, String nick, String photo, String gender,
        Date startDate, Date lastDate, String category, String description) {
    this.no = no;
    this.id = id;
    this.email = email;
    this.password = password;
    this.nick = nick;
    this.photo = photo;
    this.gender = gender;
    this.startDate = startDate;
    this.lastDate = lastDate;
    this.category = category;
    this.description = description;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getLastDate() {
    return lastDate;
  }

  public void setLastDate(Date lastDate) {
    this.lastDate = lastDate;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Member [no=" + no + ", id=" + id + ", email=" + email + ", password=" + password + ", nick=" + nick
        + ", photo=" + photo + ", gender=" + gender + ", startDate=" + startDate + ", lastDate=" + lastDate
        + ", category=" + category + ", description=" + description + "]";
  }


}
