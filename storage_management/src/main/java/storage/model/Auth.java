package storage.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "auth")
public class Auth {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID", nullable = false)
    private Menu menu;
    
    @Column(name = "PERMISSION", nullable = false)
    private Integer permission;
    
    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;
    
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;
    
    // Constructors
    public Auth() {}
    
    public Auth(Role role, Menu menu, Integer permission, Integer activeFlag) {
        this.role = role;
        this.menu = menu;
        this.permission = permission;
        this.activeFlag = activeFlag;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.updateDate = new Timestamp(System.currentTimeMillis());
    }
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }
    
    public Integer getPermission() { return permission; }
    public void setPermission(Integer permission) { this.permission = permission; }
    
    public Integer getActiveFlag() { return activeFlag; }
    public void setActiveFlag(Integer activeFlag) { this.activeFlag = activeFlag; }
    
    public Timestamp getCreateDate() { return createDate; }
    public void setCreateDate(Timestamp createDate) { this.createDate = createDate; }
    
    public Timestamp getUpdateDate() { return updateDate; }
    public void setUpdateDate(Timestamp updateDate) { this.updateDate = updateDate; }
    
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
