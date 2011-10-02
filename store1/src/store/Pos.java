package store;

public class Pos {

	private BarcodeScanner scanner; 
	
	public Pos(BarcodeScanner scanner) { 
		this.scanner = scanner; 
	}
	
	public void scanProduct() {
		scanner.scan(); 
		
	}

}
