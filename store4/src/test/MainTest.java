package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/*
 * Tests: 
 * 
 * There is a POS, it has a handler for the barcode scanned event.
 * 
 * done Scanning barcode 11111 the display displays price 21.13
 * 
 * done Scanning barcode 33333 the display displays price 33.17
 * 
 * done Scanning unknown barcode 22222 it displays "No price for 22222" 
 * 
 * Scanning empty barcode it displays "Empty barcode scanned"
 */

public class MainTest {

	static class Pos {
		private final Display display;
		private final Map<String, String> barcodeToPriceMap;
		
		public Pos(Display display, Map<String, String> barcodeToPriceMap) {
			super();
			this.display = display;
			this.barcodeToPriceMap = barcodeToPriceMap;
		}

		void onBarcodeScanned(final String barcode) {
			String price = barcodeToPriceMap.get(barcode);
			if (price != null) 
				display.display(price);
			else 
				display.display("No price for " + barcode); 
		}
	}
	
	static class Display {
		private String lastDisplayedString;
		
		void display(final String s) { 
			lastDisplayedString = s; 
		}

		public String getLastDisplayedString() {
			return lastDisplayedString; 
		}
	}

	private Display display; 
	private Pos pos; 
	private Map<String, String> barcodeToPriceMap;
	
	@SuppressWarnings("serial")
	@Before 
	public void testSetup() { 
		display = new Display();  

		barcodeToPriceMap = new HashMap<String, String>() { {
			put("11111", "21.13"); 
			put("irrelevant_data", "irrelevant_data"); 
		};
		};
		
		pos = new Pos(display, barcodeToPriceMap); 
	}
	
	@Test
	public void testScanKnownBarcode() {
		final String barcode = "11111"; 
		
		pos.onBarcodeScanned(barcode); 
		
		assertEquals(barcodeToPriceMap.get(barcode), display.getLastDisplayedString()); 
	}

	

	@Test
	public void testScanUnknownBarcode() {
		final String barcode = "22222"; 
		
		pos.onBarcodeScanned(barcode); 
		
		assertEquals("No price for " + barcode, display.getLastDisplayedString()); 
	}



}
