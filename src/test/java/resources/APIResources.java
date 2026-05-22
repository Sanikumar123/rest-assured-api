package resources;

public enum APIResources {
	
	LOGINAPI("/auth/login"),
	ADDPRODUCT("/product/add-product"),
	ADDORDER("/order/create-order"),
	VIEWORDER("/order/get-orders-details"),
	DELETEPRODUCT("/product/delete-product");
	
	String resource;

	APIResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}

}
