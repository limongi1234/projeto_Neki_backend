package br.org.neki.backend.dto;

import br.org.neki.backend.model.Image;
import br.org.neki.backend.model.Level;
import br.org.neki.backend.model.User;

public class ChangeUserDTO 
{
   private Long id;
   private String name, email, password;
   private Image img;
   private Level level;

   public ChangeUserDTO(User user) 
   {
	  super();
   }

	public ChangeUserDTO(Long id, String name, String email, String password, Image img, Level level)
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.img = img;
		this.level = level;
	}
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Image getImg() 
	{
		return img;
	}

	public void setImg(Image img) 
	{
		this.img = img;
	}

	public Level getLevel() 
	{
		return level;
	}

	public void setLevel(Level level) 
	{
		this.level = level;
	}

	@Override
	public String toString() 
	{
		return "ChangeUserDTO [name = " + name + ", email =  " + email + 
				", password = " + password + ", img = " + img +
				", level =" + level + "]";
	}
}
