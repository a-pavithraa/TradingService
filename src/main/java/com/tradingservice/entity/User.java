package com.tradingservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id	
	private Long id;

	@Column(nullable=false, unique=true)
	@NotEmpty
	@Email(message="Invalid email")
	private String email;
	
	@Column(nullable=false)
	@NotEmpty
	@Size(min=4)
	private String password;

	@Column(nullable=false)
	@NotEmpty()
	private String name;

	@JsonIgnore
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
	      name="user_role",
	      joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
	      inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	private List<Role> roles = new ArrayList<>();
	
}
