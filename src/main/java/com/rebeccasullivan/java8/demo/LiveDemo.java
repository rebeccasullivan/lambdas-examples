package com.rebeccasullivan.java8.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.*;

import com.rebeccasullivan.java8.models.Artist;
import com.rebeccasullivan.java8.models.Doubler;
import com.rebeccasullivan.java8.models.DoublerImpl;
import com.rebeccasullivan.java8.models.Track;

public class LiveDemo {

	public static void main(String[] args) {

		// Functional Interfaces
		
		// Pre Java 8 - option 1
		Doubler doubler = new DoublerImpl();
		doubler.doubleMe(5);
		
		// option 2
		Doubler doublerAnon = new DoublerImpl() {
			@Override 
			public int doubleMe(int n) {
				return n * 2;
			}
		};
		
		// option 3
		Doubler doublerLambda = n -> n * 2;
		doublerLambda.doubleMe(5);
		
		
		Predicate<Artist> isAmerican = artist -> 
									"American".equals(artist.getNationality());
		
		Function<Track, Integer> getLengthInMinutes = track -> track.getLength() / 60;
		Track track = new Track("Somewhere, Over the Rainbow", 180);
		Integer lengthInMin = getLengthInMinutes.apply(track);
		
		// Stream Operations
		List<Artist> artists = new ArrayList<>();
		artists.stream()
				.filter(a -> "The Beatles".equals(a.getName()))
				.count();
		
		
		
	}

	private void toy(List<Integer> values) {
		Predicate<Integer> isGreaterThan3 = e -> e > 3;
		Predicate<Integer> isDivisibleBy2 = e -> e % 2 == 0;
		Function<Integer, Integer> doubleInt = e -> e * 2;
		
		values.stream()
			  .filter(isGreaterThan3)
			  .filter(isDivisibleBy2)
			  .map(doubleInt)
			  .findFirst()
			  .orElse(0);
		
		
		// Imperative
		int result = 0;
		
		for (int e : values) {
			if (e > 3 && e % 2 == 0) {
				result = e * 2;
				break;
			}
		}
	}
}
