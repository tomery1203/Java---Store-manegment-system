package com.hit.dao;

import java.io.Serializable;
import java.util.List;

import com.hit.dm.VideoGame;


public interface Dao <ID extends Serializable, T>{
		
		List<T> read(String s);
		
		void save(T t,String s);

		void delete(T t,String s);
		
		void write (	List<T>t,String s);
		
		void rent (String s);
		
		void reeturn (String s);

	}



