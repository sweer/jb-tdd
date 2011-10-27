package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/*
 * Req-ts: 
 * 
 * Scanning barcode I get price of the corresponding product on the display. 
 * 
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
 * done Scanning empty barcode it displays "Empty barcode scanned"
 * 
 * At this point it's evident we've view, controller and model all in one Pos class. 
 * String variable called "price" stinks. 
 * 
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
			if (barcode.equals("")) { 
				display.display("Empty barcode scanned"); 
				return;
			}
			
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

	@Test
	public void testScanEmptyBarcode() {
		final String barcode = ""; 
		
		pos.onBarcodeScanned(barcode); 
		
		assertEquals("Empty barcode scanned", display.getLastDisplayedString()); 
	}

	
}
