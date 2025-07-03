package storage.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "ROLE_NAME", length = 50, nullable = false)
    private String roleName;
    
    @Column(name = "DESCRIPTION", length = 200)
    private String description;
    
    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;
    
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;
    
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles;
    
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Auth> auths;
    
    // Constructors
    public Role() {}
    
    public Role(String roleName, String description, Integer activeFlag) {
        this.roleName = roleName;
        this.description = description;
        this.activeFlag = activeFlag;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.updateDate = new Timestamp(System.currentTimeMillis());
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getActiveFlag() {
        return activeFlag;
    }
    
    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }
    
    public Timestamp getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    
    public Timestamp getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
    
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
    
    public Set<Auth> getAuths() {
        return auths;
    }
    
    public void setAuths(Set<Auth> auths) {
        this.auths = auths;
    }
    
    @PrePersist
    protected void onCreate() {
        createDate = new Timestamp(System.currentTimeMillis());
        updateDate = new Timestamp(System.currentTimeMillis());
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateDate = new Timestamp(System.currentTimeMillis());
    }
}
