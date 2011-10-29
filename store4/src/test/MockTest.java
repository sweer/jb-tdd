package test;

import org.junit.Test;

import static org.mockito.Mockito.*;

/*
 * Req-ts: 
 * 
 * Scanning barcode I get price of the corresponding product on the display. 
 *
 * Scanning known barcode 11111 the display displays price 21.13

 * Tests: 
 * 
 * There is a POS, it has a handler for the barcode scanned event.
 * 
 * 
 * Scanning barcode 33333 the display displays price 33.17
 * 
 * Scanning unknown barcode 22222 it displays "No price for 22222" 
 * 
 * Scanning empty barcode it displays "Empty barcode scanned"
 */


public class MockTest {

	static interface Prices  {

		double getPriceByBarcode(String string);
		
	}
	
	static interface Display {

		void displayPrice(double d); 
		
	}
	
	static private class Pos {

		private final Prices prices;
		private final Display display;

		public Pos(Prices prices, Display display) {
			this.prices = prices;
			this.display = display;
		}

		public void onBarcodeScanned(String barcode) {
			display.displayPrice(prices.getPriceByBarcode(barcode));
		} 
		
	}
	
	@Test
	public final void testProductFound() {
		Prices prices = mock(Prices.class); 
		Display display = mock(Display.class);
		
		Pos pos = new Pos(prices, display);
		
		when(prices.getPriceByBarcode("11111")).thenReturn(21.13);
		
		pos.onBarcodeScanned("11111"); 
		
		verify(display).displayPrice(21.13);
	}

}
