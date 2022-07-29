package com.tradingservice.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role implements Serializable
{

	@Id	
	private Long id;
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	private String name;
		
	@ManyToMany(mappedBy="roles")
	private List<User> users;

}
