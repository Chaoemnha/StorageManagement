package storage.dto;


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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    // Getters and Setters
}

