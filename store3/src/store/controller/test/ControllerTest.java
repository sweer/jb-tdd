package store.controller.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import store.Price;
import store.controller.Controller;
import store.display.Display;
import store.model.Model;

/*
 * Test list: 
 * Scan barcode of a known product - call display to show the price, e.g. 1111 -> 20 Lt
 * Scan unknown - call display to show "No price for code " + code 
 * Empty string - call display to show "Scanning error - empty code"
 */
public class ControllerTest {

	@Test
	public final void showsErrorOnEmptyBarcode() {
		Display display = mock(Display.class);
		Model model = mock(Model.class);
		Controller controller = new Controller(display, model);

		controller.onBarcode("");

		verify(display).showEmptyBarcodeMessage();
	}

	@Test
	public final void showsPriceOfScannedKnownProduct() {
		String barcode = "1111";
		Price price = new Price(20);
		Model model = mock(Model.class);
		Display display = mock(Display.class);
		Controller controller = new Controller(display, model);
		when(model.getPriceByProduct(barcode)).thenReturn(price);

		controller.onBarcode(barcode);

		verify(display).showPrice(price);
	}

	@Test
	public final void showsUnknownProductMessage() {
		String barcode = "1112";
		Model model = mock(Model.class);
		Display display = mock(Display.class);
		Controller controller = new Controller(display, model);
		when(model.getPriceByProduct(barcode)).thenReturn(null);

		controller.onBarcode(barcode);

		verify(display).showUnknownProductMessage(barcode);
	}
}
