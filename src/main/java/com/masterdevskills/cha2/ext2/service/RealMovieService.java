/*
 * #%L
 * Advanced Java LIVE course-2020
 * %%
 * Copyright (C) 2020 MasterDevSkills.com
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package com.masterdevskills.cha2.ext2.service;

import com.masterdevskills.cha2.ext2.model.Movie;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 07 August 2020
 */
public class RealMovieService {


	/**
	 * TODO: count the Movie object stored in InMemoryMovieService
	 *
	 * @return number of movies stored in the InMemoryMovieService
	 * @see Stream#count()
	 */
	public long countMovies() {
		List<Movie> allMovies = getAllMovies();
		return allMovies.stream()
						.count();
	}

	private List<Movie> getAllMovies() {
		return InMemoryMovieService.getInstance().findAllMovies();
	}

	/**
	 * TODO: find all the movies released in a particular year
	 *
	 * @param year given year
	 * @return list of Movies in a particular year
	 * @see java.util.stream.Stream#filter(Predicate)
	 * @see java.util.stream.Stream#collect(Collector)
	 */
	public List<Movie> findAllMoviesInYear(int year) {

		List<Movie> allMovies = getAllMovies();

		return allMovies.stream()
				.filter(movie -> String.valueOf(year).equals(movie.getYear()))
				.collect(Collectors.toList());
	}

	/**
	 * TODO: given a rating, return the list of the movies of that rating
	 *
	 * @param rated given rating
	 * @return list of movies of that rating
	 * @see java.util.stream.Stream#filter(Predicate)
	 * @see java.util.stream.Stream#collect(Collector)
	 */

	public List<Movie> findAllMovieRated(String rated) {

		List<Movie> allMovies = getAllMovies();

		return allMovies.stream()
				.filter(movie -> rated.equals(movie.getRated()))
				.collect(Collectors.toList());
	}

	/**
	 * TODO: given a rating, return the count of the movies of that rating
	 *
	 * @param rated given rating
	 * @return count of movies of that rating
	 * @see java.util.stream.Stream#filter(Predicate)
	 * @see java.util.stream.Stream#collect(Collector)
	 */

	public long countMoviesWithRated(String rated) {

		List<Movie> allMovies = getAllMovies();

		return allMovies.stream()
				.filter(movie -> rated.equals(movie.getRated()))
				.count();
	}

	/**
	 * TODO: given a rating, return list of movies whose ratings are equal or greater than given rating
	 *
	 * @param rating given rating
	 * @return list of movies whose ratings are equal or greater than given rating
	 * @see java.util.stream.Stream#filter(Predicate)
	 * @see java.util.stream.Stream#collect(Collector)
	 */
	public List<Movie> findMoviesWithImdbRatingEqualAndGreaterThan(double rating) {

		List<Movie> allMovies = getAllMovies();

		return allMovies.stream()
				.filter(movie -> movie.getImdbRating() >= rating)
				.collect(Collectors.toList());
	}

	/**
	 * TODO: return list of movies that are directed by given director name
	 *
	 * @param director name of director
	 * @return list of movies that are directed by given director name
	 * @see java.util.stream.Stream#filter(Predicate)
	 * @see java.util.stream.Stream#collect(Collector)
	 */
	public List<Movie> findMoviesOfDirector(String director) {

		List<Movie> allMovies = getAllMovies();

		return allMovies.stream()
				.filter(movie -> director.equals(movie.getDirector()))
				.collect(Collectors.toList());
	}

	/**
	 * TODO: return list of Movie Title of those movies whose rating is equal to given rating
	 *
	 * @param rated given rating
	 * @return list of Movie Title of those movies whose rating is equal to given rating
	 */
	public List<String> listMovieTitleRated(String rated) {

		List<Movie> allMovies = getAllMovies();

		return allMovies.stream()
				.filter(movie -> rated.equals(movie.getRated()))
				.map(Movie::getTitle)
				.collect(Collectors.toList());
	}

	/**
	 * TODO: return list of distinct movie title of that movie, whose rating is equal to given rating
	 *
	 * @param rated given rating
	 * @return list of distinct movie title of that movie, whose rating is equal to given rating
	 * @see java.util.stream.Stream#filter(Predicate)
	 * @see java.util.stream.Stream#collect(Collector)
	 * @see Stream#distinct()
	 */
	public List<String> listUniqueMovieTitleRated(String rated) {

		List<Movie> allMovies = getAllMovies();

		return allMovies.stream()
				.filter(movie -> rated.equals(movie.getRated()))
				.map(Movie::getTitle)
				.distinct()
				.collect(Collectors.toList());
	}

	/**
	 * TODO: return movie title of any movie whose rating is equal or greater than given rating
	 *
	 * @param rating given rating
	 * @return movie title of any movie whose rating is equal or greater than given rating
	 * @see Stream#findAny()
	 */
	public Optional<String> findAnyMovieTitleWithImdbRatingEqualOrGreater(double rating) {

		List<Movie> allMovies = getAllMovies();

		return allMovies.stream()
				.filter(movie -> movie.getImdbRating() == rating)
				.map(Movie::getTitle)
				.findAny();
	}

	/**
	 * TODO: return movie title of the first movie whose rating is equal or greater than given rating
	 *
	 * @param rating given rating
	 * @return name of the first movie with given rating, empty if not
	 * @see Stream#findFirst()
	 * @see Stream#map(Function)
	 */
	public Optional<String> findFirstMovieTitleWithImdbRatingEqualOrGreater(double rating) {

		List<Movie> allMovies = getAllMovies();

		return allMovies.stream()
				.filter(movie -> movie.getImdbRating() == rating)
				.findFirst()
				.map(Movie::getTitle);
	}

	/**
	 * TODO: sort all the movies by their title and return the sorted list
	 *
	 * @return the sorted list
	 * @see Stream#sorted(Comparator)
	 */
	public List<Movie> sortMovieByTitle() {

		List<Movie> allMovies = getAllMovies();

		Comparator<Movie> stringComparator = (movie1, movie2) -> movie1.getTitle().compareToIgnoreCase(movie2.getTitle());

		return allMovies.stream()
				.sorted(stringComparator)
				.collect(Collectors.toList());
	}

	/**
	 * TODO: sort all the movies by their imdb rating, then by their title(if rating are equal) and return the sorted list
	 *
	 * @return the sorted list
	 * @see Stream#sorted(Comparator)
	 * @see Comparator#thenComparing(Function)
	 */
	public List<Movie> sortByImdbRatingAndThenTitle() {

		List<Movie> allMovies = getAllMovies();

		Comparator<Movie> ratingComparator = Comparator.comparing(Movie::getImdbRating);
		Comparator<Movie> titleComparator = (movie1, movie2) -> movie1.getTitle().compareToIgnoreCase(movie2.getTitle());

		Comparator<Movie> comparator = ratingComparator.thenComparing(titleComparator);

		return allMovies.stream()
				.sorted(comparator)
				.collect(Collectors.toList());
	}

	/**
	 * TODO: find top rated movie from all the movies and return the Movie
	 *
	 * @return movie with maximum rating
	 * @see Stream#max(Comparator)
	 */

	public Optional<Movie> findTopRatedMovie() {

		List<Movie> allMovies = getAllMovies();

		Comparator<Movie> ratingComparator = Comparator.comparing(Movie::getImdbRating);

		return allMovies.stream()
				.max(ratingComparator);
	}

	/**
	 * TODO: find min rated movie from all the movies and return the Movie
	 *
	 * @return movie with minimum rating
	 */

	public Optional<Movie> findMinRatedMovie() {

		List<Movie> allMovies = getAllMovies();

		Comparator<Movie> ratingComparator = Comparator.comparing(Movie::getImdbRating);

		return allMovies.stream()
				.min(ratingComparator);
	}

	/**
	 * TODO: find number of Distinct Movies of each director
	 *
	 * @return list of Distinct movies
	 */

	public Map<String, Long> findNumberOfDistinctMoviesOfEachDirector() {
		throw new RuntimeException("TODO://ImplementIt");
	}

	/**
	 * TODO:  find number movies title by years comma separated
	 *
	 * @return map containing year and movie tiles comma separated
	 */
	public Map<String, String> getMoviesByYear() {
		throw new RuntimeException("TODO://ImplementIt");
	}
}


