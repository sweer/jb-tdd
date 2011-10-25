package store.display;

import store.Price;

public interface Display {

	void showPrice(Price price);

	void showEmptyBarcodeMessage();

	void showUnknownProductMessage(String barCode);

}
