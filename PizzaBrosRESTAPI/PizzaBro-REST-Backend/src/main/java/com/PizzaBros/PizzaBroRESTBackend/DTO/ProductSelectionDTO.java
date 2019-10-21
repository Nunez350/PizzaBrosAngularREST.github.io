package com.PizzaBros.PizzaBroRESTBackend.DTO;

public class ProductSelectionDTO {
	
	private Long productId;
	private String name;
	private Double price;
	private String imageURL;
	private Integer quantity;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	public ProductSelectionDTO(Long productId, String name, Double price,  Integer quantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ProductSelectionDTO [productId=" + productId + ", name=" + name + ", price=" + price + ", quantity="
		+ quantity + "]";
	}
}
