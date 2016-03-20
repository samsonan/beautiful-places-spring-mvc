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

/**
 * Site user entity.
 * 
 * Validated by com.samsonan.bplaces.util.validation.UserFormValidator
 * @author Andrey Samsonov (samsonan)
 *
 */
@Entity
@Table(name="bplace_user")
public class User { 
	
	/**
	 * User may have one role at a time
	 */
	public final static String ROLE_USER = "ROLE_USER";
	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	
	//email is not confirmed
	public final static int STATUS_PENDING = 0;
	//email is confirmed. USers created by admin are active by default
	public final static int STATUS_ACTIVE = 1;  
	
	public final static String [] ROLE_LIST = {ROLE_USER, ROLE_ADMIN};
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
	
	/**
	 * login and display name
	 */
    @NotEmpty
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

    @NotEmpty
	@Column(name="password")
    private String password;

    @Transient
    private String confirmPassword;
    
    /**
     * user role. See ROLE_* constants
     */
	@Column(name="role")
    private String role;

	/**
	 * user status. See STAUS_* constants
	 */
	@Column(name="status")
    private int status;
	
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
		return (id == null);
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
