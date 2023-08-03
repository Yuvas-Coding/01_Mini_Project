package com.vtalent.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Plan_category")
public class PlanCategory {
	
	@Id
	@GeneratedValue
	@Column(name = "Category_id")
	private Integer categoryId;
	
	@Column(name = "Category_Name")
	private String categoryName;
	
	@Column(name = "Active_Switch")
	private String activeSwitch;
	
	@Column(name = "Created_By")
	private String createdBY;
	
	@Column(name = "Updated_By")
	private String updatedBy;
	
	@Column(name = "Created_Date",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name = "Updated_Date",insertable = false)
	private LocalDate updateDate;

}
