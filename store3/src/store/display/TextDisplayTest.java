package store.display;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import store.Price;

/* Methods to test
 * void showPrice(Price price);

void showEmptyBarcodeMessage();

void showUnknownProductMessage();
*/

public class TextDisplayTest {

	private TextDisplay display; 

	
	@Before
	public void setUpDisplay() { 
		display = new TextDisplay(); 
	}
	
	@Test
	public void showEmptyBarcodeMessage() {
		
		display.showEmptyBarcodeMessage(); 
		
		assertEquals("Scanning error - empty code", display.getText());
	}

	@Test
	public void showUnknownProductMessage() { 
		String barCode = "1111";

		display.showUnknownProductMessage(barCode); 
		
		assertEquals("No price for code " + barCode, display .getText()); 
	}
	
	@Test
	public final void showPrice() { 
		int price = 20; 
		
		display.showPrice(new Price(price));
		
		assertEquals(price + " Ls", display.getText()); 
	}

	@Test
	public final void showAnotherPrice() { 
		int price = 25; 
		
		display.showPrice(new Price(price));
		
		assertEquals(price + " Ls", display.getText()); 
	}

	

}
