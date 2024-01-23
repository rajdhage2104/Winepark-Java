package com.cybage.winepark.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "wine")
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer wineId;
    @NotNull
    private Integer quantity;
    @NotNull
    private String category;
    @NotNull
    private String name;
    @NotNull
    private Double price;
	public Integer getWineId() {
		return wineId;
	}
	public void setWineId(Integer wineId) {
		this.wineId = wineId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Wine [wineId=" + wineId + ", quantity=" + quantity + ", category=" + category + ", name=" + name
				+ ", price=" + price + "]";
	}
	public Wine(Integer wineId, @NotNull Integer quantity, @NotNull String category, @NotNull String name,
			@NotNull Double price) {
		super();
		this.wineId = wineId;
		this.quantity = quantity;
		this.category = category;
		this.name = name;
		this.price = price;
	}
	public Wine() {
		super();
		
	}
	

}
