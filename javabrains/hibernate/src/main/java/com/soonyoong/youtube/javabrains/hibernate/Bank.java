package com.soonyoong.youtube.javabrains.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Bank {
	
	@Id
	@GeneratedValue
	private int bankId;
	
	private String bankName;
	//comment out for less issue when delete the DB, can use one
/*	@ElementCollection              //can use for collection of basic or composite class, has this annotation enough
	@CollectionTable(name = "BANK_CONTACT", joinColumns = @JoinColumn(name = "BANK_ID"))  //can further config the table
	@Column(name = "NAME")
	private Collection<String> contacts = new ArrayList<String>();
	
	@ElementCollection
	@CollectionTable(name="BANK_CONTACT_MAP", joinColumns= {@JoinColumn(name="BANK_ID", referencedColumnName = "bankid")})
	@MapKeyColumn(name="CONTACT_MAP_KEY")
	@Column(name="FULL_NAME")
	private Map<String, String> contactMap = new HashMap<String, String>();*/
	
	
	//@CollectionTable(name="BANK_CAMPAIGN_CONDITION_MAP", joinColumns=@JoinColumn(name="BANK_ID")) name = "BANK_CAMPAIGN_CONDITION_MAP", 
	//@JoinTable(joinColumns = {@JoinColumn(name = "BANK_ID", referencedColumnName = "bankid")},
	//		inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")})
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="campaign_id_col")
	@MapKey(name="participantStatus")
	private Map<ParticipantStatus, CampaignConditionExpression> campaignConditionExpressionMap;
	
	
	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

/*	public Collection<String> getContacts() {
		return contacts;
	}

	public void setContacts(Collection<String> contacts) {
		this.contacts = contacts;
	}

	public Map<String, String> getContactMap() {
		return contactMap;
	}

	public void setContactMap(Map<String, String> contactMap) {
		this.contactMap = contactMap;
	}*/

	public Map<ParticipantStatus, CampaignConditionExpression> getCampaignConditionExpressionMap() {
		return campaignConditionExpressionMap;
	}

	public void setCampaignConditionExpressionMap(
			Map<ParticipantStatus, CampaignConditionExpression> campaignConditionExpressionMap) {
		this.campaignConditionExpressionMap = campaignConditionExpressionMap;
	}
	
}
