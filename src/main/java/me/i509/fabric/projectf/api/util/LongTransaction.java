package me.i509.fabric.projectf.api.util;

import org.checkerframework.checker.nullness.qual.Nullable;

public interface LongTransaction extends AutoCloseable {
	long getPrevious();

	long getCurrent();

	long getExtra();

	void cancel();

	boolean isCancelled();

	LongTransaction.Action getAction();

	@Override
	void close();

	static LongTransaction create(long previous, long current, long extra, Action action) {
		return LongTransaction.create(previous, current, extra, action, null);
	}

	static LongTransaction create(long previous, long current, long extra, Action action, @Nullable SubmitCallback callback) {
		return new LongTransactionImpl(previous, current, extra, action, callback);
	}

	enum Action {
		ACT,
		SIMULATE;

		public boolean isSimulating() {
			return this == Action.SIMULATE;
		}
	}

	@FunctionalInterface
	interface SubmitCallback {
		static SubmitCallback union(@Nullable SubmitCallback callback, @Nullable SubmitCallback other) {
			return transaction -> {
				if (callback != null) {
					callback.submit(transaction);
				}

				if (other != null) {
					other.submit(transaction);
				}
			};
		}

		void submit(LongTransaction transaction);
	}
}
