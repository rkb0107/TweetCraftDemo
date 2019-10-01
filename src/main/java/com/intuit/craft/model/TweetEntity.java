package com.intuit.craft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TWEETS")
public class TweetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="twit_id")
    private Long twitId;
    
    @Column(name="user_id")
    private String userId;
    
    @Column(name="follow_id")
    private String followId;
    
    @Column(name="msg_txt", nullable=true, length=140)
    private String msgTxt;
    
    @Column(name="pub_date")
    private String publishDate;
    
    public Long getTwitId() {
		return twitId;
	}

	public void setTwitId(Long twitId) {
		this.twitId = twitId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFollowId() {
		return followId;
	}

	public void setFollowId(String followId) {
		this.followId = followId;
	}

	public String getMsgTxt() {
		return msgTxt;
	}

	public void setMsgTxt(String msgTxt) {
		this.msgTxt = msgTxt;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	@Override
    public String toString() {
        return "TweetEntity [twitId=" + twitId + ", userId=" + userId + 
                ", followId=" + followId + ", msgTxt=" + msgTxt   + 
                ", publishDate=" + publishDate   +"]";
    }
}