package br.org.neki.backend.dto;

import br.org.neki.backend.model.Image;
import br.org.neki.backend.model.Level;
import br.org.neki.backend.model.User;

public class CreateUserDTO 
{
   private User user;
   private UserDTO userDTO;
   private ChangeUserDTO changeUserDTO;

   public CreateUserDTO() 
   {
	  super();
   }

	public User getUser() 
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}

	public UserDTO getUserDTO() 
	{
		return userDTO;
	}
	
	public void setUserDTO(UserDTO userDTO) 
	{
		this.userDTO = userDTO;
	}

	public ChangeUserDTO getChangeUserDTO()
	{
		return changeUserDTO;
	}
	
	public void setChangeUserDTO(ChangeUserDTO changeUserDTO) 
	{
		this.changeUserDTO = changeUserDTO;
	}

	public String getPassword(String password) 
	{
		return password;
	}

	public String getName(String name)
    {
		return name;
	}

	public Image getImg(Image img) 
	{
		return img;
	}

	public Level getLevel(Level level)
	{
		return level;
	}

	public String getEmail(String email) 
	{
		return email;
	}

	public CharSequence getPassword(CharSequence password) 
	{
		return password;
	}
}
	
