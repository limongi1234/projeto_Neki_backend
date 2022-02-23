package br.org.neki.backend.model;

public class JwtResponse 
{
	private String token;
	private String type = "Bearer";
	private Long id;
	private String email;
	private Image image;
	private String name;
	private String level;
	
	public JwtResponse(String accessToken, Long id, String email, Image image, String name, String level) 
	{
		this.token = accessToken;
		this.id = id;
		this.email = email;
		this.image = image;
		this.name = name;
		this.level = level;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public Image getImage() 
	{
		return image;
	}

	public void setImage(Image image) 
	{
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLevel() 
	{
		return level;
	}

	public void setLevel(String level) 
	{
		this.level = level;
	}
}
    
	

	
	
