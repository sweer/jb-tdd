package store;

public final class Price {
	private final int amount;

	public Price(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return amount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (amount != other.amount)
			return false;
		return true;
	}

	public int getAmount() {
		return amount;
	} 
	
	
	
	
}
