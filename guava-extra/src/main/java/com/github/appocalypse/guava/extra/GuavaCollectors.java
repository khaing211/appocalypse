package com.github.appocalypse.guava.extra;

/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import static java.util.stream.Collector.Characteristics.UNORDERED;

import java.util.Comparator;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

/**
 * https://gist.githubusercontent.com/JakeWharton/9734167/raw/9c33f52cc0eec4f207dc021543a8aa283eb8152d/GuavaCollectors.java
 */

/** 
 * Stream {@link Collector collectors} for Guava types. 
 */
public final class GuavaCollectors {
	/** Collect a stream of elements into an {@link ImmutableList}. */
	public static <T> Collector<T, ImmutableList.Builder<T>, ImmutableList<T>> immutableList() {
		return Collector.of(ImmutableList.Builder<T>::new,
				ImmutableList.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableList.Builder<T>::build);
	}

	/** Collect a stream of elements into an {@link ImmutableSet}. */
	public static <T> Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> immutableSet() {
		return Collector.of(ImmutableSet.Builder<T>::new,
				ImmutableSet.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableSet.Builder<T>::build, UNORDERED);
	}

	/** 
	 * Collect a stream of elements into an {@link ImmutableSortedSet}. 
	 * Using natural order
	 */
	public static <T extends Comparable<?>> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> immutableSortedSet() {
		return Collector.of(ImmutableSortedSet::<T>naturalOrder,
				ImmutableSortedSet.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableSortedSet.Builder<T>::build);
	}
	
	/** 
	 * Collect a stream of elements into an {@link ImmutableSortedSet}. 
	 * Using reverse order
	 */
	public static <T extends Comparable<?>> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> immutableSortedSetReverseOrder() {
		return Collector.of(ImmutableSortedSet::<T>reverseOrder,
				ImmutableSortedSet.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableSortedSet.Builder<T>::build);
	}
	
	/** Collect a stream of elements into an {@link ImmutableSortedSet}. */
	public static <T> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> immutableSortedSet(Comparator<T> comparator) {
		return Collector.of(() -> ImmutableSortedSet.orderedBy(comparator),
				ImmutableSortedSet.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableSortedSet.Builder<T>::build);
	}
	
	private GuavaCollectors() {
		throw new AssertionError("No instances.");
	}
}
