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

package me.i509.fabric.projectf.article;

import me.i509.fabric.projectf.api.article.FMCArticle;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.PacketByteBuf;

public final class DummyFMCArticle implements FMCArticle {
	public static final DummyFMCArticle DUMMY = new DummyFMCArticle();

	public static String getTranslationKey(FMCArticle fmcArticle) {
		return "fmc.article";
	}

	public static FMCArticle read(Tag tag) {
		return DummyFMCArticle.DUMMY;
	}

	public static Tag write(FMCArticle fmcArticle) {
		return ByteTag.ZERO;
	}

	public static FMCArticle readPacket(PacketByteBuf byteBuf) {
		return DummyFMCArticle.DUMMY;
	}

	public static void writePacket(FMCArticle fmcArticle, PacketByteBuf byteBuf) {
		// NO-OP
	}

	private DummyFMCArticle() {
		// NO-OP
	}
}
