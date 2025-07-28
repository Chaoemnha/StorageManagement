package storage.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "PARENT_ID")
    private Integer parentId;
    
    @Column(name = "URL", length = 100)
    private String url;
    
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;
    
    @Column(name = "ORDER_INDEX", nullable = false)
    private Integer orderIndex;
    
    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;
    
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;
    
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Auth> auths;
    
    private List<Menu> child;
    
    private String idMenu;
    
    // Constructors
    public Menu() {}
    
    public Menu(Integer parentId, String url, String name, Integer orderIndex, Integer activeFlag) {
        this.parentId = parentId;
        this.url = url;
        this.name = name;
        this.orderIndex = orderIndex;
        this.activeFlag = activeFlag;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.updateDate = new Timestamp(System.currentTimeMillis());
    }
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getParentId() { return parentId; }
    public void setParentId(Integer parentId) { this.parentId = parentId; }
    
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
    
    public Integer getActiveFlag() { return activeFlag; }
    public void setActiveFlag(Integer activeFlag) { this.activeFlag = activeFlag; }
    
    public Timestamp getCreateDate() { return createDate; }
    public void setCreateDate(Timestamp createDate) { this.createDate = createDate; }
    
    public Timestamp getUpdateDate() { return updateDate; }
    public void setUpdateDate(Timestamp updateDate) { this.updateDate = updateDate; }
    
    public Set<Auth> getAuths() { return auths; }
    public void setAuths(Set<Auth> auths) { this.auths = auths; }
    
    public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

	public String getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(String idMenu) {
		this.idMenu = idMenu;
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
