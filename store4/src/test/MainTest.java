package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/*
 * Req-ts: 
 * There is a POS, it has a handler for the barcode scanned event.
 * Some products have GST 5% and some additional PST 6% on top of it. 
 * Price display shall include G or GP indicator. 
 * Pressing "total" shall display cost = price + all applicable taxes. 
 * 
 * Tests: 
 * 
 * done Scanning unknown barcode 22222 it displays "No price for 22222" 
 * 
 * done Scanning empty barcode it displays "Empty barcode scanned"
 *
 * done Scanning 11111 displays 21.13, 
 * done clicking Total then displays 21.13 
 * 
 * done Scanning 33333 displays 33.17 G, 
 * clicking Total then displays 34.83
 * 
 * Scanning 44444 displays 444.11 GP, clicking Total then displays 494.29  
 * 
 * Clicking Total after total price displayed does nothing. 
 * 
 * Clicking Total before scanning anything displays "Scan something first".
 *
 * 
 * Notes: 
 * 
 * We've controller and model all in one Pos class. 
 * Double math arithmetics is dangerous. 
 * 
 */

public class MainTest {

	enum TaxType { 
		NO_TAX, GST, GST_AND_PST;  
	}
	
	static final class Price { 
		private final double price; 
		private final TaxType taxType;

		
		public double getPrice() {
			return price;
		}
		public TaxType getTaxType() {
			return taxType;
		}
		public Price(double price, TaxType taxType) {
			super();
			this.price = price;
			this.taxType = taxType;
		}   
	}
	
	static class Pos {
		private final Display display;
		private final Map<String, Price> barcodeToPriceMap;
		
		public Pos(Display display, Map<String, Price> barcodeToPriceMap) {
			super();
			this.display = display;
			this.barcodeToPriceMap = barcodeToPriceMap;
		}

		void onBarcodeScanned(final String barcode) {
			if (barcode.equals("")) { 
				display.displayEmptyBarcodeScanned(); 
				return;
			}
			
			Price price = barcodeToPriceMap.get(barcode);
			if (price != null) {  
				display.displayPrice(price.getPrice(), price.getTaxType());  
			} else {  
				display.displayBarcodeNotFound(barcode); 
			}
		}

		public void onTotal() {
			// TODO Auto-generated method stub
			
		}
	}
	
	static class Display {
		private String lastDisplayedString;
		private final Map<TaxType, String> taxLabel = new HashMap<TaxType, String>() {{
			put(TaxType.NO_TAX, ""); 
			put(TaxType.GST, " G"); 
		}}; 
		public void displayBarcodeNotFound(String barcode) {
			sendToDisplay("No price for " + barcode);
		}

		public void displayPrice(double price, TaxType taxType) {
			sendToDisplay(Double.toString(price) + taxLabel.get(taxType));
		}


		public void displayEmptyBarcodeScanned() {
			sendToDisplay("Empty barcode scanned"); 
		}

		void sendToDisplay(String s) { 
			lastDisplayedString = s; 
		}
		
		public String getLastDisplayedString() {
			return lastDisplayedString; 
		}
	}

	
	
	private Display display; 
	private Pos pos; 
	private Map<String, Price> barcodeToPriceMap;
	
	@SuppressWarnings("serial")
	@Before 
	public void testSetup() { 
		display = new Display();  

		barcodeToPriceMap = new HashMap<String, Price>() { {
			put("11111", new Price(21.13, TaxType.NO_TAX)); 
			put("33333", new Price(33.17, TaxType.GST)); 
		};
		};
		
		pos = new Pos(display, barcodeToPriceMap); 
	}
	
	@Test
	public void testScanKnownBarcode() {
		final String barcode = "11111"; 
		
		pos.onBarcodeScanned(barcode); 
		
		assertEquals(Double.toString(barcodeToPriceMap.get(barcode).getPrice()), display.getLastDisplayedString()); 
	}

	@Test
	public void testScanKnownBarcode2() {
		final String barcode = "33333"; 
		
		pos.onBarcodeScanned(barcode); 
		
		assertEquals(Double.toString(barcodeToPriceMap.get(barcode).getPrice()) + " G", display.getLastDisplayedString()); 
	}

//	@Test
//	public void testTotalKnownBarcode2() {
//		final String barcode = "33333"; 
//		
//		pos.onBarcodeScanned(barcode);
//		
//		pos.onTotal();
//		
//		assertEquals(String.format("%.2f", barcodeToPriceMap.get(barcode).getPrice()*1.05), display.getLastDisplayedString()); 
//	}
	
	
	
	@Test
	public void testTotalKnownBarcode() {
		final String barcode = "11111"; 
		
		pos.onBarcodeScanned(barcode);
		
		pos.onTotal();
		
		assertEquals(Double.toString(barcodeToPriceMap.get(barcode).getPrice()), display.getLastDisplayedString()); 
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
