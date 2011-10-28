package store.display;

import store.Price;

public class TextDisplay implements Display {

	private String text;

	public String getText() {
		return text; 
	}

	public void showUnknownProductMessage(String barCode) {
		text = "No price for code " + barCode; 
	}

	public void showEmptyBarcodeMessage() {
		text = "Scanning error - empty code";
	}

	public void showPrice(Price price) {
		text = price.getAmount() + " Ls";
	}

}
