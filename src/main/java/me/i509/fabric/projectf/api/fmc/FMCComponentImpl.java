package me.i509.fabric.projectf.api.fmc;

import me.i509.fabric.projectf.api.util.LongTransaction;
import net.minecraft.nbt.CompoundTag;
import org.checkerframework.checker.nullness.qual.Nullable;

final class FMCComponentImpl implements FMCComponent {
	private long value;
	private long capacity;

	FMCComponentImpl(long capacity) {
		this.value = 0L;
		this.capacity = capacity;
	}

	FMCComponentImpl(long value, long capacity) {
		this.value = value;
		this.capacity = capacity;
	}

	@Override
	public long getAmount() {
		return this.value;
	}

	@Override
	public long getCapacity() {
		return this.capacity;
	}

	@Override
	public void setAmount(long amount) {
		this.value = amount;
	}

	@Override
	public LongTransaction recieve(LongTransaction.Action action, long amount, LongTransaction.@Nullable SubmitCallback callback) {
		final long newValue = this.value + amount;
		long extra = 0L;

		if (this.capacity < newValue) {
			extra = newValue - this.capacity;
		}

		return LongTransaction.create(this.value, newValue, extra, action, LongTransaction.SubmitCallback.union(callback, transaction -> {
			if (!transaction.isCancelled() && !transaction.getAction().isSimulating()) {
				this.value = newValue;
			}
		}));
	}

	@Override
	public LongTransaction remove(LongTransaction.Action action, long amount, LongTransaction.@Nullable SubmitCallback callback) {
		final long newValue = this.value - amount;
		long extra = 0L;

		if (0 > newValue) {
			extra = newValue;
		}

		return LongTransaction.create(this.value, newValue, extra, action, LongTransaction.SubmitCallback.union(callback, transaction -> {
			if (!transaction.isCancelled() && !transaction.getAction().isSimulating()) {
				this.value = newValue;
			}
		}));
	}

	@Override
	public void fromTag(CompoundTag tag) {
		this.capacity = tag.getLong("Capacity");
		this.value = tag.getLong("Value");
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag.putLong("Capacity", this.capacity);
		tag.putLong("Value", this.value);
		return tag;
	}
}
