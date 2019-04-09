package com.agro.wallet.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class AuditedEntity {
	
	@Column(name="create_ts", nullable=false)
	@CreatedDate
	private Date createTs;
	
	@Column(name="modified_ts", nullable=false)
	@LastModifiedDate
	private Date modifyTs;
	
	@Column(name = "created_by", length = 20, nullable = true)
    private String createdBy;
	
	@Column(name = "modified_by", length = 20, nullable = true)
	private String modifiedBy;

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public Date getModifyTs() {
		return modifyTs;
	}

	public void setModifyTs(Date modifyTs) {
		this.modifyTs = modifyTs;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


}
