package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "USER_DETAILS")
@NamedQuery(name="UserDetails.byId", query="from UserDetails where userId = ?")
@NamedNativeQuery(name = "UserDetails.byName", query = "SELECT * FROM USER_DETAILS where userName = ?", resultClass=UserDetails.class)
public class UserDetails {
	@Id
	@GeneratedValue
	private int userId;

	private String userName;
	private Date joinedDate;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="HOME_STREET")),
		@AttributeOverride(name="city", column=@Column(name="HOME_CITY")),
		@AttributeOverride(name="state", column=@Column(name="HOME_STATE")),
		@AttributeOverride(name="pinCode", column=@Column(name="HOME_PINCODE"))})
	private Address homeAddress;
	@Lob
	private String description;
	@Lob
	private byte[] imageThumb;
	

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + "]";
	}

	public int getUserId() {
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

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImageThumb() {
		return imageThumb;
	}

	public void setImageThumb(byte[] imageThumb) {
		this.imageThumb = imageThumb;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

}
