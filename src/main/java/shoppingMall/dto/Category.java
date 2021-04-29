package shoppingMall.dto;

public class Category {
	private String categoryCode;
	private String categoryName;

	public Category() {}

	public Category(String categoryCode, String categoryName) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}

	

	public Category(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryCode == null) ? 0 : categoryCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryCode == null) {
			if (other.categoryCode != null)
				return false;
		} else if (!categoryCode.equals(other.categoryCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s %s", categoryCode, categoryName == null ? "" : categoryName);
	}
	
}
