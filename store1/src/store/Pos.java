package store;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Pos {

	private BarcodeScanner scanner;
	private Display display;
	private ProductList productList;
	private long productCode;

	public Pos(BarcodeScanner scanner, Display display, ProductList productList) {
		this.scanner = scanner;
		this.display = display;
		this.productList = productList;
	}

	public void scanProduct() {
		productCode = scanner.scan();
	}

	public void showPrice() {
		Product product = productList.getProduct(productCode);
		if (product == null) {
			display.show("No price found for " + productCode);
		} else {
			display.show(product.getPrice().toString());
		}
	}

}
