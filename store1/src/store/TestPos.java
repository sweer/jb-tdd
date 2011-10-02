package store;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TestPos {
	private Pos pos; 
    private BarcodeScanner scanner; 
    
	@Before
	public void posSetup() { 
		BarcodeScanner scanner = mock(BarcodeScanner.class); 
		pos = new Pos(scanner); 
	}
	
	@Test
	public final void scanCallsTheScanner() {
		pos.scanProduct(); 
		verify(scanner).scan(); 
	}

}
