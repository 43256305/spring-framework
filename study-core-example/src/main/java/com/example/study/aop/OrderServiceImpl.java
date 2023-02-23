package com.example.study.aop;

/**
 * @author ：xjh
 * @date ：Created in 2023/2/21 10:55
 * @description：
 * @modified By：
 * @version:
 */
public class OrderServiceImpl implements OrderService {


	@Override
	public Order createOrder(String username, String product) {
		Order order = new Order();
		order.setUsername(username);
		order.setProduct(product);
		return order;
	}

	@Override
	public Order queryOrder(String username) {
		Order order = new Order();
		order.setUsername("username");
		order.setProduct("product");
		return order;
	}
}
