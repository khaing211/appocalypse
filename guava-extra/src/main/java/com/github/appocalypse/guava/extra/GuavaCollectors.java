package com.github.appocalypse.guava.extra;

import static java.util.stream.Collector.Characteristics.UNORDERED;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;

/**
 * https://gist.githubusercontent.com/JakeWharton/9734167/raw/9c33f52cc0eec4f207dc021543a8aa283eb8152d/GuavaCollectors.java
 */

/** 
 * Stream {@link Collector collectors} for Guava types. 
 */
public interface GuavaCollectors {
	/** Collect a stream of elements into an {@link ImmutableList}. */
	public static <T> Collector<T, ImmutableList.Builder<T>, ImmutableList<T>> toImmutableList() {
		return Collector.of(ImmutableList::<T>builder,
				ImmutableList.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableList.Builder<T>::build);
	}

	public static <T> Collector<ImmutableList<T>, ImmutableList.Builder<T>, ImmutableList<T>> flattenImmutableList() {
		return Collector.of(ImmutableList::<T>builder,
				(builder, element) -> builder.addAll(element), 
				(l, r) -> l.addAll(r.build()),
				ImmutableList.Builder<T>::build);
	}

	
	/** 
	 * Collect a stream of elements into an {@link ImmutableSet}. 
	 **/
	public static <T> Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> toImmutableSet() {
		return Collector.of(ImmutableSet::<T>builder,
				ImmutableSet.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableSet.Builder<T>::build, UNORDERED);
	}
	
	/** 
	 * Collect a stream of elements into an {@link ImmutableSortedSet}. 
	 * Using natural order
	 */
	public static <T extends Comparable<?>> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> 
			immutableSortedSet() {
		
		return Collector.of(ImmutableSortedSet::<T>naturalOrder,
				ImmutableSortedSet.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableSortedSet.Builder<T>::build);
	}
	
	/** 
	 * Collect a stream of elements into an {@link ImmutableSortedSet}. 
	 * Using reverse order
	 */
	public static <T extends Comparable<?>> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> 
			toImmutableSortedSetReverseOrder() {
		
		return Collector.of(ImmutableSortedSet::<T>reverseOrder,
				ImmutableSortedSet.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableSortedSet.Builder<T>::build);
	}
	
	/** 
	 * Collect a stream of elements into an {@link ImmutableSortedSet}.
	 * Using comparator
	 * */
	public static <T> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> 
			toImmutableSortedSet(Comparator<T> comparator) {
		
		return Collector.of(() -> ImmutableSortedSet.orderedBy(comparator),
				ImmutableSortedSet.Builder<T>::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableSortedSet.Builder<T>::build);
	}
	
	/**
	 * Collect a stream of elements into an {@link ImmutableMultimap}.
	 * 
	 * @param <T> the type of the input elements
     * @param <K> the output type of the key mapping function
     * @param <V> the output type of the value mapping function
	 */
	public static <T, K, V> Collector<T, ImmutableMultimap.Builder<K, V>, ImmutableMultimap<K, V>> 
		toImmutableMultimap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
		
		BiConsumer<ImmutableMultimap.Builder<K, V>, T> accumulator
        	= (builder, element) -> builder.put(keyMapper.apply(element), valueMapper.apply(element));
		
		return Collector.of(ImmutableMultimap::<K, V>builder, 
				accumulator, 
				(l, r) -> l.putAll(r.build()), 
				ImmutableMultimap.Builder<K, V>::build);
	}
	
	/**
	 * Collect a stream of elements into an {@link ImmutableMap}.
	 * Conflict key would result in override
	 * 
	 * @param <T> the type of the input elements
     * @param <K> the output type of the key mapping function
     * @param <V> the output type of the value mapping function
	 */
	public static <T, K, V> Collector<T, ImmutableMap.Builder<K, V>, ImmutableMap<K,V>> 
		toImmutableMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
		
		BiConsumer<ImmutableMap.Builder<K, V>, T> accumulator
    		= (builder, element) -> builder.put(keyMapper.apply(element), valueMapper.apply(element));
    	
		return Collector.of(ImmutableMap::<K, V>builder, 
				accumulator, 
				(l, r) -> l.putAll(r.build()), 
				ImmutableMap.Builder<K, V>::build);
	}
	
	/** 
	 * Collect a stream of elements into an {@link ImmutableSortedMap}. 
	 * Using natural order
	 * 
	 * @param <T> the type of the input elements
     * @param <K> the output type of the key mapping function
     * @param <V> the output type of the value mapping function
	 */
	public static <T, K extends Comparable<?>, V> Collector<T, ImmutableSortedMap.Builder<K, V>, ImmutableSortedMap<K, V>> 
		toImmutableSortedMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
		
		BiConsumer<ImmutableSortedMap.Builder<K, V>, T> accumulator
			= (builder, element) -> builder.put(keyMapper.apply(element), valueMapper.apply(element));
		
		return Collector.of(ImmutableSortedMap::<K, V>naturalOrder,
				accumulator, 
				(l, r) -> l.putAll(r.build()),
				ImmutableSortedMap.Builder<K, V>::build);
	}
	
	/** 
	 * Collect a stream of elements into an {@link ImmutableSortedMap}. 
	 * Using reverse order
	 * 
	 * @param <T> the type of the input elements
     * @param <K> the output type of the key mapping function
     * @param <V> the output type of the value mapping function
	 */
	public static <T, K extends Comparable<?>, V> Collector<T, ImmutableSortedMap.Builder<K, V>, ImmutableSortedMap<K, V>> 
			toImmutableSortedMapReverseOrder(Function<? super T, ? extends K> keyMapper, 
			Function<? super T, ? extends V> valueMapper) {
		
		BiConsumer<ImmutableSortedMap.Builder<K, V>, T> accumulator
			= (builder, element) -> builder.put(keyMapper.apply(element), valueMapper.apply(element));
		
		return Collector.of(ImmutableSortedMap::<K, V>reverseOrder,
				accumulator, 
				(l, r) -> l.putAll(r.build()),
				ImmutableSortedMap.Builder<K, V>::build);
	}
	
	/** 
	 * Collect a stream of elements into an {@link ImmutableSortedMap}. 
	 * Using comparator
	 * 
	 * @param <T> the type of the input elements
     * @param <K> the output type of the key mapping function
     * @param <V> the output type of the value mapping function
	 */
	public static <T, K, V> Collector<T, ImmutableSortedMap.Builder<K, V>, ImmutableSortedMap<K, V>> 
		toImmutableSortedMapReverseOrder(Comparator<K> comparator, Function<? super T, ? extends K> keyMapper, 
				Function<? super T, ? extends V> valueMapper) {
		
		BiConsumer<ImmutableSortedMap.Builder<K, V>, T> accumulator
			= (builder, element) -> builder.put(keyMapper.apply(element), valueMapper.apply(element));
		
		return Collector.of(() -> ImmutableSortedMap.<K,V>orderedBy(comparator),
				accumulator, 
				(l, r) -> l.putAll(r.build()),
				ImmutableSortedMap.Builder<K, V>::build);
	}
}
