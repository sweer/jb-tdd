package store.model;

import store.Price;

public interface Model {

	Price getPriceByProduct(String barcode);

}
