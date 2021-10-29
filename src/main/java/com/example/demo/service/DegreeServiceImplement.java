package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Degree;
import com.example.demo.repository.DegreeRepository;

@Service
public class DegreeServiceImplement implements DegreeService {
	@Autowired
	private DegreeRepository degreeRepository;

	@Override
	public List<Degree> getAllDegree() {
		return this.degreeRepository.findAll();
	}

	@Override
	public void saveDegree(Degree degree) {
		this.degreeRepository.save(degree);
		
	}

	@Override
	public Degree getDegreeById(long id) {
		Optional<Degree> optional = degreeRepository.findById(id);
		Degree degree = null;
		if(optional.isPresent()) {
			degree = optional.get();
		}
		else {
			throw new RuntimeException("Degree not found for id :: " + id);
		}
		return degree;
	}

	@Override
	public void deleteDegreeById(long id) {
		this.degreeRepository.deleteById(id);
		
	}
}
