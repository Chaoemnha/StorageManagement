package storage.model;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "product_info")
public class ProductInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATE_ID", nullable = false)
    private Category category;
    
    @Column(name = "CODE", length = 50, nullable = false)
    private String code;
    
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;
    
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "IMG_URL", length = 100)
    private String imgUrl;
    
    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;
    
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;
    
    @OneToMany(mappedBy = "productInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Invoice> invoices;
    
    @OneToMany(mappedBy = "productInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductInStock> productInStocks;
    
    @OneToMany(mappedBy = "productInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<History> histories;
    
    private MultipartFile multipartFile;
    // Constructors
    public ProductInfo() {}
    
    public ProductInfo(Category category, String code, String name, String description, 
                       String imgUrl, Integer activeFlag) {
        this.category = category;
        this.code = code;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.activeFlag = activeFlag;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.updateDate = new Timestamp(System.currentTimeMillis());
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public void setId(Integer id) {
        this.id = id;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
    
    public Set<Invoice> getInvoices() {
        return invoices;
    }
    
    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    public Set<ProductInStock> getProductInStocks() {
        return productInStocks;
    }
    
    public void setProductInStocks(Set<ProductInStock> productInStocks) {
        this.productInStocks = productInStocks;
    }
    
    public Set<History> getHistories() {
        return histories;
    }
    
    public void setHistories(Set<History> histories) {
        this.histories = histories;
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

