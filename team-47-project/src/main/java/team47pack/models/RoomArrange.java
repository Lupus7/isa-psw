package team47pack.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "roomarrange")
public class RoomArrange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long room;
	
	@Column
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date date;
	
	@Column
	private String time;
	
	@Column
	private boolean taken;
	
	public RoomArrange() {
		
	}	
	
	
	public RoomArrange(Long room, Date date, String time, boolean taken) {
		super();
		this.room = room;
		this.date = date;
		this.time = time;
		this.taken = taken;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoom() {
		return room;
	}
	public void setRoom(Long room) {
		this.room = room;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isTaken() {
		return taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	
	
}
