/*
 * MIT License
 *
 * Copyright (c) 2020 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.i509.fabric.projectf.util.collections;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.HashMap;

/**
 * Represents a HashMap which will return a default value if a key's resulting value is null.
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
public class DefaultedHashMap<K, V> extends HashMap<K, V> {
	private final V defaultValue;

	public DefaultedHashMap(V defaultValue) {
		this.defaultValue = checkNotNull(defaultValue, "The default value cannot be null.");;
	}

	/**
	 * Gets the default value.
	 * @return The default value.
	 */
	public V getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * Returns the value to which the specified key is mapped, or the default value if this map contains no mapping for the key.
	 * @param key The key.
	 * @return The value which the key has been mapped to, or the default value.
	 */
	@Override
	public V get(Object key) {
		V v = super.get(key);
		return v != null ? v : this.defaultValue;
	}
}
