
package br.org.neki.backend.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.org.neki.backend.dto.ChangeUserDTO;
import br.org.neki.backend.dto.CreateUserDTO;


@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table
public class User 
{
	private static final long serialVersionUID = 4261664564407516496L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id  integer  NOT NULL")
	private Long id;

	@Column(name = "login  varchar(12)  NOT NULL")
	private String login;

	@Column (name = "password  varchar(100)  NOT NULL")
	private String password;

	@Column(name = "last_login_date date ")
	private LocalDate last_login_date;

	private Level level;

	private String email;

	private String name;

	private Image img;

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}

	public User( @Valid ChangeUserDTO changeUserDTO)
	{

	}
	
	public User( @Valid CreateUserDTO createUserDTO)
	{

	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public LocalDate getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(LocalDate last_login_date) 
	{
		this.last_login_date = last_login_date;
	}
	
	public Level getLevel() 
	{
		return level;
	}

	public String getName()
	{
		return name;
	}

	public Image getImg() 
	{
		return img;
	}
	
	public void setLevel(Level level) 
	{
		this.level = level;
		
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setImg(Image img) 
	{
		this.img = img;
	}

	@Override
	public String toString() 
	{
		return "User [id = " + id + ", login = " + login + 
				", password = " + password + ", last_login_date = " + "]";
	}

	@Override
	public int hashCode() 
	{
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	public String getEmail() 
	{
		return email;
	}
}
