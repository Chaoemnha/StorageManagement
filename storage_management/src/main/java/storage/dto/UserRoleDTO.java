package storage.dto;

import javax.servlet.http.HttpSession;

public class UserRoleDTO {
    private Integer id;
    private Integer userId;
    private String userName;
    private Integer roleId;
    private String roleName;

    // Constructors
    public UserRoleDTO() {}
    public UserRoleDTO(Integer id, Integer userId, String userName, Integer roleId, String roleName) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.roleId = roleId;
        this.roleName = roleName;
    }
	public Integer getRole() {
		// TODO Auto-generated method stub
		return roleId;
	}

    // Getters and Setters
}

