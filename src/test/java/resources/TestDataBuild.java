package resources;


import pojo.OrdersRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojo.LoginRequest;
import pojo.OrderRequest;

public class TestDataBuild {
	
	public LoginRequest getLoginRequestPayload(String username, String password)
	{
	
	LoginRequest loginRequest = new LoginRequest();
	loginRequest.setUserEmail(username);
	loginRequest.setUserPassword(password);
	return loginRequest;
	
	}
	
	
	public OrdersRequest createorderRequestPayload(String country, String productId)
	{
	
	OrderRequest addOrderRequest = new OrderRequest();
	addOrderRequest.setCountry(country);
	addOrderRequest.setProductOrderedId(productId);
	
	OrdersRequest OrderRequest = new OrdersRequest();
	List<OrderRequest> orders = new ArrayList<OrderRequest>();
	orders.add(addOrderRequest);
	OrderRequest.setOrders(orders);
	
	return OrderRequest;
	
	
	}
	
	
	

}
