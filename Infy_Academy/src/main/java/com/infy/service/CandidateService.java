package com.infy.service;

import java.util.Map;

import org.apache.commons.configuration2.ex.ConfigurationException;

import com.infy.exception.InfyAcademyException;
import com.infy.model.Candidate;
import com.infy.model.CandidateReport;

public interface CandidateService {
	
	String addCandidate(Candidate candidate) throws InfyAcademyException, ConfigurationException;
	String calculateGrade(CandidateReport candidateReportTO);
	Map<Integer, String> getGradesForAllCandidates() throws InfyAcademyException;
	
}
