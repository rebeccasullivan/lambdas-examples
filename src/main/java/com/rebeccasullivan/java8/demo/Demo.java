package com.rebeccasullivan.java8.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.rebeccasullivan.java8.models.Artist;
import com.rebeccasullivan.java8.models.Doubler;
import com.rebeccasullivan.java8.models.Navigation;
import com.rebeccasullivan.java8.models.Page;
import com.rebeccasullivan.java8.models.Track;

public class Demo {
	public static void main(String[] args) {
		System.out.println("Hello, world!");

		/*
		 * 	Examples will start out more contrived, just to get a feel for the basics.
		 *	Then, we'll turn to some more practical examples that I pulled from work.
		 *
		 * ------------------------------------------------------------------------------------
		 * 
		 * 	FUNCTIONAL INTERFACES: 
		 * 		- building blocks of functional programming in Java 8. 
		 * 		- Definition - an interface with only one abstract method (single
		 * 			abstract method), so the interface will only represent one behavior. The SAM
		 * 			is also called the functional method.
		 * 
		 * 		- You can still define default methods (in order to maintain compatibility
		 * 			with previous versions), but it must only have a SAM.
		 * 
		 *	So, before Java 8 was around, you'd have to use anonymous inner classes
		 *	to achieve the same functionality.
		 *
		 *	**Show Example**
		 */
		// Option 1: impl class
		 Doubler doubler = new DoublerImpl();
		 int result = doubler.doubleMe(5);
		
		// Option 2: anonymous class
		Doubler doublerAnon = new Doubler() {
			@Override
			public int doubleMe(int n) {
				return n * 2;
			}
		};
		doublerAnon.doubleMe(5);
		
		// Option 3: lambdas
		Doubler doublerLambda = n -> n * 2;
		doublerLambda.doubleMe(5);
		
		/*	A lambda expression is a method without a name that is used to pass around 
		 *	behavior as if it were data.
		 *
		 *	GO TO SLIDE
		 *
		 *	There are several basic function shapes, including:
		 *		- Function (unary function from T to R)
		 *		- Consumer (unary function from T to void)
		 *		- Predicate (unary function from T to boolean)
		 *		- Supplier (nilary function to R)
		 */
		
		/*
		 *	As you'll see, a lot of stream operations take in one of these interfaces
		 *	as an argument, so a lot of the time you don't need to create your own
		 *	if you're just looking to hook into Streams API.
		 *
		 *	Examples: Predicate, Function, BinaryOperator, Collector, Comparator
		 */
		
		/*
		 * PREDICATE: 
		 * 	- functional interface whose SAM is 'test'
		 * 	- Used in a lot of the streams operations to determine when elements of a collection
		 * 		meet a certain condition 
		 * 	- You can chain predicates using AND, OR, NEGATE
		 * 	- Java gives you type inference - we don't need to declare the type of 'artist' since
		 * 		we already specified it in the type declaration of the predicate. This is similar
		 * 		to diamond notation where we can write Map<String, String> myMap = new HashMap<>();
		 * 
		 * Example: 
		 */
		Predicate<Artist> matchByNationality = artist -> "American".equals(artist.getNationality());
		Predicate<Artist> matchByName = artist -> "The Beatles".equals(artist.getName());
		
		Artist artist = new Artist("The Beatles", "British");
		boolean matches = matchByNationality.and(matchByName).test(artist);
		
		/*
		 * FUNCTION: 
		 * 	- functional interface whose SAM is 'apply'
		 * 	- Takes in a single input type, define output type 
		 * 	- Used in map and flatMap operations
		 */
		Function<Track, Integer> getLengthInMinutes = track -> track.getLength() / 60;
		Track track = new Track("Somewhere, Over the Rainbow", 180);
		Integer lengthInMin = getLengthInMinutes.apply(track);
		
		/*
		 * 	BINARY OPERATOR:
		 * 	- Takes in a single input type, with two arguments of that type, and returns the same type 
		 * 	- Is a special case (implementation) of BiFunction, which takes in two arguments of some type
		 * 	- BiFunction is a special case of Function
		 * 	- method 'andThen' off BiFunction allows you to chain the binary operator into another function
		 */
		BinaryOperator<Integer> addEmUp = (a, b) -> a + b;
		addEmUp.apply(1, 2);
		
		
		/*
		 * 	COLLECTOR:
		 * 	- Most common ones come with java.util.stream.Collectors
		 * 	- If you wanted to roll your own, you'd need to implement the Collector interface and define a: 
		 * 		- Supplier - function to create a results container
		 * 		- Accumulator - function to accumulate (add) elements to results container
		 * 		- Combiner - for parallel operations, function that determines how to combine the results
		 * 
		 * 	- You can use Collector.of( ) and supply a supplier, accumulator, and combiner, and it really 
		 * 			starts to look like JavaScript with callback functions
		 * 
		 * 		// GO TO SLIDE
		 * 
		 * 	- Collectors supplies OOTB implementations that are common (show methods available) 
		 */
		Collectors.toList();
		Collectors.toSet();
		
		
		/* 
		 * 	SPOTTING A LAMBDA EXPRESSION - different syntaxes:
		 * 	- No-argument version, need empty parentheses to signify no arguments
		 * 	- One argument: no need for type, but you can add type with parentheses if desired
		 */
		
		Supplier<String> mySupplier = () -> "Hello, World!";
		Function<Integer, Integer> myFunction = a -> a * 8;
		Function<Integer, Integer> myExplicitlyTypedFunction = (Integer a) -> a * 8;
		
		BinaryOperator<Integer> myBinaryOp = (a, b) -> {
			System.out.println(a);
			return a + b;
		};
		
		/*
		 * STREAM OPERATIONS:
		 * Show stream() and parallelStream() from collections interface
		 * stream() is similar to calling iterator in external iteration - allows you to access streams API
		 */
		List<Artist> artists = new ArrayList<>();
		

		/*	Operations can be grouped into intermediate and terminal operations:
		 *	- Show method signatures: 
		 *		- intermediate return another stream and are lazy. You're basically defining a 'recipe' 
		 *			to be evaluated once a terminal operation is applied. 
		 *
		 *	- Terminal operations = eager
		 */
		
		/*
		 ***** Intermediate operations: ***** 
		 * FILTER: supply a predicate and return another stream with elements that pass the predicate test
		 */
		Predicate<Artist> matchesByName = a -> "Norah Jones".equals(a.getName());
		
		// This won't actually do anything until a terminal operation is called!
		artists.stream().filter(matchesByName);
		
		// But adding a terminal operation will force the filter to evaluate
		artists.stream()
				.filter(matchesByName)
				.count();
		
		// MAP: supply a function that converts from one stream into (possibly) different-typed stream
		// 	- avoid boilerplate of iterating through collection and performing mapping with external iteration
		artists.stream()
				.map(artiste -> artiste.getName().toUpperCase())
				.collect(Collectors.toList());
		
		
		/* Others: flatMap, peek (debugging purposes)
		 * 
		 * 
		 ***** Terminal operations: *****
		 * anyMatch, allMatch, noneMatch
		 * 
		 * findFirst, findAny
		 * 
		 * forEach
		 * 
		 * reduce
		 * 		special cases of reduction: count, min, max
		 */
		List<Integer> numbers = new ArrayList<>();
		numbers.stream().reduce(0, (a, b) -> a + b);
		
		// Imperative example - source is IBM Developer docs, by Venkat Subramaniam
		List<Integer> values = Arrays.asList(4, 5, 20, 11, 0, 2);
		
		int result = 0;
		
		for(int e : values) {
		  if(e > 3 && e % 2 == 0) {
		    result = e * 2;
		    break;
		  }
		}
		
		// Refactored using lambdas
		int resultWithLambdas = values.stream()
									  .filter(e -> e > 3)
								  	  .filter(e -> e % 2 == 0)
								  	  .map(e -> e * 2)
								  	  .findFirst()
								  	  .orElse(0);
		/*
		 * EXAMPLES
		 * 
		 * 1) Logging library (see slides)
		 * 		- Symptoms: 
		 * 			- querying state on object just to push it right back into the class
		 * 			- performance cost of creating objects or performing operations unnecessarily 	
		 */
		
		/*
		 * Example ideas
		 * 
		 *  - Data sheets - data munging
		 *  - Navigation in header
		 *  - Reflection for PDP - avoiding WET with behavior
		 *  - Comparator examples - using an anonymous class = overkill, and using a comparator can be a lot more succinct
		 */
	}
	
	public void navExample(List<Navigation> navs, Page selectedPage) {
		// Using external iteration - you could extract out the boolean check if you wanted
		// to make this more expressive
		for (Navigation nav : navs) {
			boolean isActive = selectedPage.getPath().equals(nav.getLinkUrl());
			
			if (isActive) {
				nav.setActive(true);
			}
		}

		// First pass, using forEach - not much benefit, right? We've made the iteration internal,
		// but all the complexity is still there and we haven't accomplished much.
		navs.stream()
			.forEach(nav -> {
				if (selectedPage.getPath().equals(nav.getLinkUrl())) {
					nav.setActive(true);
				}
			});
		
		
		// Refactored, using filter, map, reduce
		String path = selectedPage.getPath();
		
		navs.stream()
			.filter(nav -> nav.getLinkUrl().equals(path));
		
		// Refactored, with lambda extracted out
		
		
//		if (currentlySelectedDivisionChild != null) {
//			// Set active class to true for currently selected item in main nav
//			navs.stream().forEach(nav -> {
//				if (currentlySelectedDivisionChild != null && currentlySelectedDivisionChild.getPath().equals(nav.getPagePath())) {
//					nav.setActive(true);
//				}
//			});
//			
//			navs.stream()
//			.filter(nav -> currentlySelectedDivisionChild != null && currentlySelectedDivisionChild.getPath().equals(nav.getPagePath()))
//			.map(nav -> nav.setActive(true));
//			
//		}
	}

}
