package com.jevoncode.spring.services;

import com.jevoncode.spring.dao.MovieDao;

public class MovieStoreServiceImpl implements MovieStoreService {

	private MovieDao movieDao;

	public void createMovie() {
		movieDao.saveMovie();
	}

	/**
	 * spring的set注入
	 * @param movieDao
	 */
	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

}
