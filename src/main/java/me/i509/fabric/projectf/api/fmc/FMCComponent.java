package me.i509.fabric.projectf.api.fmc;

import me.i509.fabric.projectf.api.util.LongTransaction;
import nerdhub.cardinal.components.api.component.Component;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface FMCComponent extends Component {
	long getAmount();

	long getCapacity();

	void setAmount(long amount);

	default LongTransaction recieve(LongTransaction.Action action, long amount) {
		return this.recieve(action, amount, null);
	}

	LongTransaction recieve(LongTransaction.Action action, long amount, LongTransaction.@Nullable SubmitCallback callback);

	default LongTransaction remove(LongTransaction.Action action, long amount) {
		return this.remove(action, amount, null);
	}

	LongTransaction remove(LongTransaction.Action action, long amount, LongTransaction.@Nullable SubmitCallback callback);

	static FMCComponent create(long amount, long capacity) {
		if (amount < 0) {
			throw new IllegalArgumentException("Initial amount cannot be less than 0");
		}

		if (capacity < 0) {
			throw new IllegalArgumentException("Max capacity cannot be less than 0");
		}

		if (amount > capacity) {
			amount = capacity;
		}

		return new FMCComponentImpl(amount, capacity);
	}

	static FMCComponent create(long capacity) {
		return FMCComponent.create(0, capacity);
	}
}
