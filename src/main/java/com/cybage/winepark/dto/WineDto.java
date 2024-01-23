package com.cybage.winepark.dto;

import lombok.*;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
public class WineDto {
    private Integer wineId;
    private Integer quantity;
    private String category;
    private String name;
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
	public WineDto(Integer wineId, Integer quantity, String category, String name, Double price) {
		super();
		this.wineId = wineId;
		this.quantity = quantity;
		this.category = category;
		this.name = name;
		this.price = price;
	}
	@Override
	public String toString() {
		return "WineDto [wineId=" + wineId + ", quantity=" + quantity + ", category=" + category + ", name=" + name
				+ ", price=" + price + "]";
	}
	public WineDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
  
}
