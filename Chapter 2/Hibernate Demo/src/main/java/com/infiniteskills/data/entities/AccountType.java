package com.infiniteskills.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity()
@Table(name = "ACCOUNT_TYPE")
public class AccountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_TYPE_ID")
	private Long accountTypeId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LAST_UPDATED_DATE")
	private Date LastUpdatedDate;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Transient	//then this properties will not be stored into DB
	private String noStoreDBValue;

	public Long getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastUpdatedDate() {
		return LastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getNoStoreDBValue() {
		return noStoreDBValue;
	}

	public void setNoStoreDBValue(String noStoreDBValue) {
		this.noStoreDBValue = noStoreDBValue;
	}

	@Override
	public String toString() {
		return "AccountType [accountTypeId=" + accountTypeId + ", name=" + name + ", LastUpdatedDate=" + LastUpdatedDate
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", noStoreDBValue=" + noStoreDBValue + "]";
	}
	
}
