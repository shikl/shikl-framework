package cn.shikl.data.jpa.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 实体父类.
 */
@MappedSuperclass
public class BaseEntity {

    @Column(
            name = "OPR_DATE"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date oprDate;
    @Column(
            name = "OPR_USER_ID"
    )
    private String oprUserId;
    @Column(
            name = "TENANT_KEY"
    )
    private String tenantKey;
    @Version
    @Column(
            name = "VERSION"
    )
    private Long version;

    public Date getOprDate() {
        return oprDate;
    }

    public void setOprDate(Date oprDate) {
        this.oprDate = oprDate;
    }

    public String getOprUserId() {
        return oprUserId;
    }

    public void setOprUserId(String oprUserId) {
        this.oprUserId = oprUserId;
    }

    public String getTenantKey() {
        return tenantKey;
    }

    public void setTenantKey(String tenantKey) {
        this.tenantKey = tenantKey;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
