package storage.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;
    
    @Column(name = "CODE", length = 50, nullable = false)
    private String code;
    
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;
    
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductInfo> productInfos;
    
    // Constructors
    public Category() {}
    
    public Category(String name, String code, String description, Integer activeFlag) {
        this.name = name;
        this.code = code;
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
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
    
    public Set<ProductInfo> getProductInfos() {
        return productInfos;
    }
    
    public void setProductInfos(Set<ProductInfo> productInfos) {
        this.productInfos = productInfos;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name = "+name+" Code = "+code+" description = "+description;
	}
    
}