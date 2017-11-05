package com.be.web.entities;

import java.sql.Timestamp;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractEntity {
	
	@Column(name = "create_time")
	protected Timestamp createTime;
	
	@Column(name = "update_time")
	protected Timestamp updateTime;

}
