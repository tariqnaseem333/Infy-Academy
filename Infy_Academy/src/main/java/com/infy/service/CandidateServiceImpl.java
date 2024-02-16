package com.infy.service;

import com.infy.model.Candidate;
import com.infy.model.CandidateReport;
import com.infy.validator.Validator;

import java.util.Map;

import com.infy.dao.CandidateDAO;
import com.infy.dao.CandidateDAOImpl;
import com.infy.exception.InfyAcademyException;

public class CandidateServiceImpl implements CandidateService {

//	Instance Variable
	private CandidateDAO candidateDAO = new CandidateDAOImpl();

	// can have result as 'P' only if all 3 marks are 50 and above
	@Override
	public String addCandidate(Candidate candidate) throws InfyAcademyException {
		Validator validation = new Validator();
		if( validation.validate(candidate).equals("Successfully Validated") ) {
			Character tempResult = 'P';
			if( candidate.getMark1() < 50 || candidate.getMark2() < 50 || candidate.getMark3() < 50  ) {
				tempResult = 'F';
			}
			if( !candidate.getResult().equals(tempResult) ) {
				return "Result should be 'F' (Fail) if student scores less than 50 in any one subject";
			}
			return candidateDAO.addCandidate(candidate);
		} else {
			return validation.validate(candidate);
		}
	}

	// calculating grade for candidate based on his marks and result
	@Override
	public String calculateGrade(CandidateReport candidateReportTO) {
		String grade = null;
		if( candidateReportTO.getResult() == 'F' ) {
			grade = "NA";
		} else {
			float average = ( candidateReportTO.getMark1() + candidateReportTO.getMark2() + candidateReportTO.getMark3() ) / 3F;
			if( average >= 85.0 )
				grade = "A";
			else if( average >= 75.0 && average < 85.0 )
				grade = "B";
			else
				grade = "C";
		}
		return grade;
	}

	// populating String[] by calling calculateGrade(candidateReportTO) and returning the same.
	@Override
	public Map<Integer, String> getGradesForAllCandidates() throws InfyAcademyException {
		CandidateReport[] candidatesData = candidateDAO.getAllCandidates();
		String[] gradesForAllCandidates = new String[candidatesData.length];
		for( int i = 0; i < candidatesData.length; i++ ) {
			gradesForAllCandidates[i] = candidatesData[i].getCandidateId().toString() + ":" + this.calculateGrade(candidatesData[i]);
		}
		return gradesForAllCandidates;
	}
}
