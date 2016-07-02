package org.effectivejava.examples.chapter06.item33;

import java.util.EnumMap;
import java.util.Map;

/**
 * Using a nested EnumMap to associate data with enum pairs - Page 163-164
 *
 * @since 2016. 7. 2.
 * @author Digitanomad
 */
public enum Phase {
	SOLID, LIQUID, GAS;
	
	public enum Transition {
		MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID), BOIL(LIQUID, GAS), CONDENSE(
				GAS, LIQUID), SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
		
		private final Phase src;
		private final Phase dst;
		
		Transition(Phase src, Phase dst) {
			this.src = src;
			this.dst = dst;
		}
		
		private static final Map<Phase, Map<Phase, Transition>> m = new EnumMap<Phase, Map<Phase,Transition>>(Phase.class);
		static {
			for (Phase p : Phase.values()) {
				m.put(p, new EnumMap<Phase, Transition>(Phase.class));
			}
			for (Transition trans : Transition.values()) {
				m.get(trans.src).put(trans.dst, trans);
			}
		}
		
		public static Transition from(Phase src, Phase dst) {
			return m.get(src).get(dst);
		}
	}
	
	// Simple demo program - prints a sloppy table
	public static void main(String[] args) {
		for (Phase src : Phase.values()) {
			for (Phase dst : Phase.values()) {
				if (src != dst) {
					System.out.printf("%s to %s : %s %n", src, dst,
					Transition.from(src, dst));
				}
			}
		}
			
	}
}
