package storage.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product_in_stock")
public class ProductInStock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProductInfo productInfo;
    
    @Column(name = "QTY", nullable = false)
    private Integer qty;
    
    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;
    
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;
    
    // Constructors, getters, setters, and lifecycle methods...
    public ProductInStock() {}
    
    public ProductInStock(ProductInfo productInfo, Integer qty, Integer activeFlag) {
        this.productInfo = productInfo;
        this.qty = qty;
        this.activeFlag = activeFlag;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.updateDate = new Timestamp(System.currentTimeMillis());
    }
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public ProductInfo getProductInfo() { return productInfo; }
    public void setProductInfo(ProductInfo productInfo) { this.productInfo = productInfo; }
    
    public Integer getQty() { return qty; }
    public void setQty(Integer qty) { this.qty = qty; }
        
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
