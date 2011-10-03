package store;

import java.math.BigDecimal;

public class Product {
	private final long code; 
	private final BigDecimal price;
	public long getCode() {
		return code;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public Product(long code, BigDecimal price) {
		this.code = code;
		this.price = price;
	} 

	public Product(long code, String price) {
		this.code = code;
		this.price = new BigDecimal(price);
	} 
	
}
