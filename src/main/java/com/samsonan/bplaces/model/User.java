package com.samsonan.bplaces.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.samsonan.bplaces.util.validation.ValidEmail;

@Entity
@Table(name="bplace_user")
public class User { 
	
	public final static String ROLE_USER = "ROLE_USER";
	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	
	public final static String [] ROLE_LIST = {ROLE_USER, ROLE_ADMIN};
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
	
    @NotEmpty(message = "{NotEmpty.user.name}")
	@Column(name="name")
    private String name;

    @ValidEmail
    @NotEmpty
	@Column(name="email")
    private String email;

	@Column(name="first_name")
    private String firstName; 

	@Column(name="last_name")
    private String lastName; 

    @NotEmpty(message = "{NotEmpty.user.password}")
	@Column(name="password")
    private String password;

    @Transient
    private String confirmPassword;
    
	@Column(name="role")
    private String role;
   
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isNew() {
		return (id == 0);
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@Override
    public int hashCode() {
		return new HashCodeBuilder(17, 37).
			       append(name).
			       append(email).
			       toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		User other = (User) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(name, other.name)
				.append(email, other.email).isEquals();
	}
 
    @Override
    public String toString() {
        return "User [id=" + id + ", name="+name+", ROLE=" + role + "]";
    }
    
    
	
} 
