package com.PizzaBros.PizzaBroRESTBackend.DTO;

import java.util.Arrays;

import com.PizzaBros.PizzaBroRESTBackend.DTO.ProductSelectionDTO;

public class CartDTO {
	
	private ProductSelectionDTO[] selections;
	private int itemCount;
	private double totalPrice;

	public ProductSelectionDTO[] getSelections() {
		return selections;
	}

	public void setSelections(ProductSelectionDTO[] selections) {
		this.selections = selections;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartDTO [selections=" + Arrays.toString(selections) + ", itemCount=" + itemCount + ", totalPrice="
				+ totalPrice + "]";
	}


}
