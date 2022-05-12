package com.soonyoong.youtube.javabrains.hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CAMPAIGN_CONDITION_EXPRESSION")
public class CampaignConditionExpression {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
    private Integer id;
	
	@Column(name = "campaign_id_col")
    private Integer campaignId;
    
	@Enumerated(EnumType.STRING)
    @Column(name = "participant_status")
    private ParticipantStatus participantStatus;

    private String expression;
    
    

	public CampaignConditionExpression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CampaignConditionExpression(Integer campaignId, ParticipantStatus participantStatus, String expression) {
		super();
		//this.id = id;
		this.campaignId = campaignId;
		this.participantStatus = participantStatus;
		this.expression = expression;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	public ParticipantStatus getParticipantStatus() {
		return participantStatus;
	}

	public void setParticipantStatus(ParticipantStatus participantStatus) {
		this.participantStatus = participantStatus;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

}
