package com.prateek.learnspring.entity;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.prateek.learnspring.model.SentMessageAndRating;
import com.prateek.learnspring.model.SongAndRating;
import com.prateek.learnspring.model.UserMessage;

import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.ConstructorResult;

@SqlResultSetMappings({
@SqlResultSetMapping(name="SongAndRatingDtoMapping",
	classes = {
			@ConstructorResult(targetClass=SongAndRating.class, 
					columns = {
							@ColumnResult(name="songId", type=String.class),
							@ColumnResult(name="songName", type=String.class),
							@ColumnResult(name="link", type=String.class),
							@ColumnResult(name="rating", type=Float.class)
			})
	}),
@SqlResultSetMapping(name="MessageAndRatingDtoMapping", 
	classes = {
			@ConstructorResult(targetClass=UserMessage.class, 
					columns = {
							@ColumnResult(name="messageId", type=String.class),
							@ColumnResult(name="fromId", type=String.class),
							@ColumnResult(name="firstName", type=String.class),
							@ColumnResult(name="lastName", type=String.class),
							@ColumnResult(name="email", type=String.class),
							@ColumnResult(name="songId", type=String.class),
							@ColumnResult(name="name", type=String.class),
							@ColumnResult(name="link", type=String.class)
					})
	}),
@SqlResultSetMapping(name="SentAndRatingDtoMapping",
	classes = {
			@ConstructorResult(targetClass=SentMessageAndRating.class,
					columns = {
							@ColumnResult(name="link", type=String.class),
							@ColumnResult(name="name", type=String.class),
							@ColumnResult(name="firstName", type=String.class),
							@ColumnResult(name="lastName", type=String.class),
							@ColumnResult(name="rating", type=Float.class)
					})
	})
})

@Entity
@Table(
		name="rating",
		uniqueConstraints = {
				@UniqueConstraint(
						columnNames = {"songId", "userId"},
						name="songId_userId"
				)
		}
)
public class Rating {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String songId;
	
	private String userId;
	
	private float rating;

	public Rating(String songId, String userId, float rating) {
		super();
		this.songId = songId;
		this.userId = userId;
		this.rating = rating;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
}
