package com.su.calculator;

import org.springframework.stereotype.Service;

@Service
public class Calculator {
	public int sum(int a, int b) {
		return a+b;
	}

}
