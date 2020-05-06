package me.i509.fabric.projectf.api.util;

import org.checkerframework.checker.nullness.qual.Nullable;

final class LongTransactionImpl implements LongTransaction {
	private final long previous;
	private final long current;
	private final long extra;
	private final LongTransaction.Action action;
	private final LongTransaction.SubmitCallback callback;
	private boolean cancelled;

	LongTransactionImpl(long previous, long current, long extra, LongTransaction.Action action, LongTransaction.@Nullable SubmitCallback callback) {
		this.previous = previous;
		this.current = current;
		this.extra = extra;
		this.action = action;
		this.callback = callback;
	}

	@Override
	public long getPrevious() {
		return this.previous;
	}

	@Override
	public long getCurrent() {
		return this.current;
	}

	@Override
	public long getExtra() {
		return this.extra;
	}

	@Override
	public void cancel() {
		this.cancelled = true;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public Action getAction() {
		return this.action;
	}

	@Override
	public void close() {
		if (!this.isCancelled() && this.getAction() == Action.ACT && this.callback != null) {
			this.callback.submit(this);
		}
	}
}
