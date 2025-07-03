package storage.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "invoice")
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "CODE", length = 50, nullable = false)
    private String code;
    
    @Column(name = "TYPE", nullable = false)
    private Integer type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProductInfo productInfo;
    
    @Column(name = "QTY", nullable = false)
    private Integer qty;
    
    @Column(name = "PRICE", precision = 15, scale = 2, nullable = false)
    private BigDecimal price;
    
    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;
    
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    
    // Constructors
    public Invoice() {}
    
    public Invoice(String code, Integer type, ProductInfo productInfo, Integer qty, 
                   BigDecimal price, Integer activeFlag, User user) {
        this.code = code;
        this.type = type;
        this.productInfo = productInfo;
        this.qty = qty;
        this.price = price;
        this.activeFlag = activeFlag;
        this.user = user;
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
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public ProductInfo getProductInfo() {
        return productInfo;
    }
    
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
    
    public Integer getQty() {
        return qty;
    }
    
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
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
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
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
