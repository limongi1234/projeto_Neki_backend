package br.org.neki.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Image 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_image")
	private Long id;

	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	private byte[] dados;

	@Column
	private String tipo;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	private User user;

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public byte[] getDados() 
	{
		return dados;
	}

	public void setDados(byte[] dados) 
	{
		this.dados = dados;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		Image other = (Image) obj;
		if (id == null) 
			if (other.id != null)
				return false;
		 else if (!id.equals(other.id))
			return false;
		return true;
	}
}
