package storage.dto;

import java.util.List;


public class RoleDTO {
	private Integer id;
	private List<AuthDTO> auths;
	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleDTO(Integer id, List<AuthDTO> auths) {
		super();
		this.id = id;
		this.auths = auths;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<AuthDTO> getAuths() {
		return auths;
	}
	public void setAuths(List<AuthDTO> auths) {
		this.auths = auths;
	}
	
}
