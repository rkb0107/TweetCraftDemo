package com.intuit.craft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name", nullable=false, length=40)
    private String userName;
    
    @Column(name="age")
    private int age;
    
    /**
     * This is another approach to fetch the list of Followers but I am using Native sql 
     * to maintain the DB schemas and relationship with another table called Follows **/
    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "follow_id")
    //private List<FollowEntity> followList = new ArrayList<>();
   
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
    public String toString() {
        return "UserEntity [userId=" + userId + ", userName=" + userName +", age=" + age + "]";
    }
}