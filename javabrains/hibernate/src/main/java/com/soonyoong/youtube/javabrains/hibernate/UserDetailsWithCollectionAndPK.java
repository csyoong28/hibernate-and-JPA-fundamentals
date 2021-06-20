package com.soonyoong.youtube.javabrains.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "USER_DETAILS_WITH_COLLECTION_AND_PK")
public class UserDetailsWithCollectionAndPK {
	@Id
	@GeneratedValue
	private int userId;

	private String userName;
	private Date joinedDate;
	
	//below is for handling a list of basic value with primary key in the join table
	@ElementCollection
	@JoinTable(name="USER_ADDRESS_WITH_PK", joinColumns=@JoinColumn(name="USER_ID"))
	@GenericGenerator(name = "hilo-gen", strategy = "hilo")
	@CollectionId(columns = { @Column(name="ADDRESS_ID") }, generator = "hilo-gen", type = @Type(type = "long"))
	private Collection<AddressWithPK> collectionOfAddress = new ArrayList<>();


	@Override
	public String toString() {
		return "UserDetailsWithCollectionAndPK [userId=" + userId + ", userName=" + userName + ", joinedDate="
				+ joinedDate + ", collectionOfAddress=" + collectionOfAddress + "]";
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

	public Collection<AddressWithPK> getCollectionOfAddress() {
		return collectionOfAddress;
	}

	public void setCollectionOfAddress(Collection<AddressWithPK> collectionOfAddress) {
		this.collectionOfAddress = collectionOfAddress;
	}

}
