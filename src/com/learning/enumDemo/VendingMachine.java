package com.learning.enumDemo;

import java.util.EnumMap;
import java.util.Iterator;
import static com.learning.enumDemo.Input.*;

public class VendingMachine {

	private static State state = State.RESTING;
	private static int amount;
	private static Input selection = null;

	enum StateDuration {
		TRANSIENT
	}

	enum State {
		RESTING { // 定义个性的方法
			void next(Input input) {
				switch(Category.categorize(input)) {
					case MONEY:
						amount += input.amount();
						state = ADDING_MONEY;
						break;
					case SHUT_DOWN:
						state = TERMINAL;
					default:
				}
			}
		},
		ADDING_MONEY {
			void next(Input input) {
				switch (Category.categorize(input)) {
				case MONEY:
					amount += input.amount();
					break;
				case ITEM_SELECTION:
					selection = input;
					if (amount < input.amount())
						System.out.println("Insufficient money for" + input);
					else
						state = DISPENSING;
				case QUIT_TRANSACTION:
					state = GIVING_CHANCE;
					break;
				case SHUT_DOWN:
					state = TERMINAL;
				default:
				}
			}
		},
		DISPENSING(StateDuration.TRANSIENT) {
			void next() {
				System.out.println("here is your " + selection);
				amount -= selection.amount();
				state = GIVING_CHANCE;
			}
		},
		GIVING_CHANCE(StateDuration.TRANSIENT) {
			void next() {
				if (amount > 0) {
					System.out.println("Your change: " + amount);
					amount = 0;
				}
				state = RESTING;
			}
		},
		TERMINAL {
			void output() {
				System.out.println("Halted");
			}
		};

		private boolean isTransient = false;

		State() {
		}

		State(StateDuration sd) {
			isTransient = true;
		}

		void next(Input input) { // 公共方法
			throw new RuntimeException("Only call next(Input input) for " + "non-transient states");
		}

		void next() {
			throw new RuntimeException("Only call next() for " + "non-transient states");
		}

		void output() {
			System.out.println(amount);
		}
	}

	public static void run(Generator gen) {
		while (state != State.TERMINAL) {
			state.next((Input) gen.next());// 枚举类型持有方法
			while (state.isTransient)
				state.next();
			state.output();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Generator<Input> gen = new RandomInputGenerator();
		run(gen);
	}

}

interface Generator<T> {
	public T next();
}

class RandomInputGenerator implements Generator<Input> {
	@Override
	public Input next() {
		return Input.randomSelection();
	}
}

enum Category {
	MONEY(NICKEL, DIME, QUARTER, DOLLAR), // 初始化
	ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP), QUIT_TRANSACTION(ABORT_TRANSACTION), SHUT_DOWN(STOP);
	private Input[] values;

	private Category(Input... types) {
		values = types; // 初始化获取Input列表
	}

	static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);

	public static Category categorize(Input input) {
		return categories.get(input);
	}

	static {
		for (Category c : Category.class.getEnumConstants()) { // 获取所有常量，遍历每个常量的初始化之后的数组，并保存起来
			for (Input in : c.values) {
				categories.put(in, c);// 对另一个enum进行分类
			}
		}
	}
}