package org.effectivejava.examples.chapter06.item34;

import java.util.Arrays;
import java.util.Collection;

import org.effectivejava.examples.chapter06.item34.Operation;

public enum ExtendedOperation implements Operation {
	EXP("^") {
		@Override
		public double apply(double x, double y) {
			return Math.pow(x, y);
		}
	},
	REMAINDER("%") {
		@Override
		public double apply(double x, double y) {
			return x % y;
		}
	};
	
	private final String symbol;
	
	ExtendedOperation(String symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return symbol;
	}
	
	// Test class to exercise all operations in "extension enum" - Page 167
	public static void main(String[] args) {
		double x = 3.1425;
		double y = 11.111;
		test(ExtendedOperation.class, x, y);
		
		System.out.println(); // Print a blank line between tests
		test2(Arrays.asList(ExtendedOperation.values()), x, y);
	}
	
	// test parameter is a bounded type token (Item 29)
	private static <T extends Enum<T> & Operation> void test(Class<T> opSet, double x, double y) {
		for (Operation op : opSet.getEnumConstants()) {
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}
	}
	
	// test parameter is a bounded wildcard type (Item 28)
	private static void test2(Collection<? extends Operation> opSet, double x, double y) {
		for (Operation op : opSet) {
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}
	}
	
	
}
