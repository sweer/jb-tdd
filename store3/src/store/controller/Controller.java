package store.controller;

import store.Price;
import store.display.Display;
import store.model.Model;

public class Controller {

	private final Display display;
	private final Model model;

	public Controller(Display display, Model model) {
		this.display = display;
		this.model = model;
	}

	public void onBarcode(String barcode) {
		if ("".equals(barcode)) {
			display.showEmptyBarcodeMessage();
			return;
		}

		Price price = model.getPriceByProduct(barcode);
		if (price != null)
			display.showPrice(price);
		else
			display.showUnknownProductMessage(barcode);
	}

}
