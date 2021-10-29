package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Degree;

public interface DegreeService {
	List<Degree> getAllDegree();
	void saveDegree(Degree degree);
	Degree getDegreeById(long id);
	void deleteDegreeById(long id);
}
