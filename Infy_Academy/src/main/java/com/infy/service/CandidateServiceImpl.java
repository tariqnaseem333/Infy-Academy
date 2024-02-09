package com.infy.service;

import com.infy.model.Candidate;
import com.infy.model.CandidateReport;
import com.infy.validator.Validator;
import com.infy.dao.CandidateDAO;
import com.infy.dao.CandidateDAOImpl;

public class CandidateServiceImpl implements CandidateService {

//	Instance Variable
	private CandidateDAO candidateDatabase = new CandidateDAOImpl();

	// can have result as 'P' only if all 3 marks are 50 and above
	@Override
	public String addCandidate(Candidate candidate) {
		Validator validation = new Validator();
		if( validation.validate(candidate).equals("Successfully Validated") ) {
			Character tempResult = 'P';
			if( candidate.getMark1() < 50 || candidate.getMark2() < 50 || candidate.getMark3() < 50  ) {
				tempResult = 'F';
			}
			if( !candidate.getResult().equals(tempResult) ) {
				return "Result should be 'F' (Fail) if student scores less than 50 in any one subject";
			}
			return candidateDatabase.addCandidate(candidate);
		} else {
			return validation.validate(candidate);
		}
	}

	// calculating grade for candidate based on his marks and result
	@Override
	public String calculateGrade(CandidateReport candidateReportTO) {
		if( candidateReportTO.getResult() == 'F' ) {
			return "NA";
		} else {
			double average = ( candidateReportTO.getMark1() + candidateReportTO.getMark2() + candidateReportTO.getMark3() ) / 3.0;
			if( average >= 85 )
				return "A";
			else if( average >= 75 && average < 85 )
				return "B";
			else if( average >= 0 && average < 75 )
				return "C";
			else
				return "Invalid Marks!!";
		}
	}

	// populating String[] by calling calculateGrade(candidateReportTO) and returning the same.
	@Override
	public String[] getGradesForAllCandidates() {
		CandidateReport[] candidatesData = candidateDatabase.getAllCandidates();
		String[] gradesForAllCandidates = new String[candidatesData.length];
		for( int i = 0; i < candidatesData.length; i++ ) {
			gradesForAllCandidates[i] = candidatesData[i].getCandidateId().toString() + ":" + this.calculateGrade(candidatesData[i]);
		}
		return gradesForAllCandidates;
	}
}
