package com.nns.thinner.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.nns.thinner.common.code.CommonCode;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {
	@Version
	public long version;
	@Column(name = "use_yn", nullable = false)
	private Boolean useYn;
	@Column(name = "create_code", nullable = false)
	private String createCode;
	@CreatedDate
	@Column(name = "create_dt", nullable = false, updatable = false)
	private LocalDateTime createDt;
	@Column(name = "modify_code")
	private String modifyCode;
	@LastModifiedDate
	@Column(name = "modify_dt", nullable = false)
	private LocalDateTime modifyDt;
	@Column(name = "delete_code")
	private String deleteCode;
	@Column(name = "delete_dt")
	private LocalDateTime deleteDt;

	@PrePersist
	public void prePersist() {
		this.createCode = CommonCode.TABLE_ROW_CREATE_NORMAL.getRawCode().toString();
		this.modifyCode = CommonCode.TABLE_ROW_UPDATE_NORMAL.getRawCode().toString();
		this.useYn = true;
	}

	@PreUpdate
	public void preUpdate() {
		this.createCode = CommonCode.TABLE_ROW_CREATE_NORMAL.getRawCode().toString();
		this.modifyCode = CommonCode.TABLE_ROW_UPDATE_NORMAL.getRawCode().toString();
		this.useYn = true;
	}




}
