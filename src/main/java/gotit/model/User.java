package gotit.model;

import java.sql.Date;

public class User {
	private long userId;
	private String userName;
	private String email;
	private String pwd;
	private String imgName;
	private String nickname;
	private int point;
	private int badge;
	private String badgeName;
	private String status;
	private Date createDate;
	private Date updateDate;
	
	public User() {}
	
	public User(long userId, String userName, String email, String pwd, String imgName, String nickname, int point,
			int badge, String status, Date createDate, Date updateDate) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.pwd = pwd;
		this.imgName = imgName;
		this.nickname = nickname;
		this.point = point;
		this.badge = badge;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

   	/* ==========================
   	 * badgeName 포함 
   	 * 뱃지아이디 + 뱃지 이름 필요할 떄
   	 * ========================== */
	public User(long userId, String userName, String email, String pwd, String imgName, String nickname, int point,
			int badge, String badgeName, String status, Date createDate, Date updateDate) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.pwd = pwd;
		this.imgName = imgName;
		this.nickname = nickname;
		this.point = point;
		this.badge = badge;
		this.badgeName = badgeName;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getpoint() {
		return point;
	}
	public void setpoint(int point) {
		this.point = point;
	}
	public int getbadge() {
		return badge;
	}
	public void setbadge(int badge) {
		this.badge = badge;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getBadge() {
		return badge;
	}

	public void setBadge(int badge) {
		this.badge = badge;
	}

	public String getBadgeName() {
		return badgeName;
	}

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
