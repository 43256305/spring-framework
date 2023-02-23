package com.example.study.aop;

/**
 * @author ：xjh
 * @date ：Created in 2023/2/21 10:54
 * @description：
 * @modified By：
 * @version:
 */
public interface OrderService {

	Order createOrder(String username, String product);

	Order queryOrder(String username);

}
