package store;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TestPos {
	private static final long UNKNOWN_CODE = 10000000000L;
	private static final String KNOWN_PRICE = "11.34";
	private static final long KNOWN_CODE = 10000000001L;
	private static final Product KNOWN_PRODUCT = new Product(KNOWN_CODE, KNOWN_PRICE);
	private Pos pos; 
    private BarcodeScanner scanner;
    private ProductList productList; 
    private Display display; 
    
	@Before
	public void posSetup() { 
		scanner = mock(BarcodeScanner.class);
		display = mock(Display.class); 
		productList = mock(ProductList.class);
		pos = new Pos(scanner, display, productList); 
	}
	
	@Test
	public final void unknownProductScannedShowsTheCode() {
		when(scanner.scan()).thenReturn(UNKNOWN_CODE); 
		pos.scanProduct();
		pos.showPrice();
		verify(display).show("No price found for " + UNKNOWN_CODE); 
	}

	@Test
	public final void knownProductScannedShowsThePrice() {
		when(scanner.scan()).thenReturn(KNOWN_CODE); 
		when(productList.getProduct(KNOWN_CODE)).thenReturn(KNOWN_PRODUCT);
		pos.scanProduct();
		pos.showPrice();
		verify(display).show(KNOWN_PRICE); 
	}

	
}
