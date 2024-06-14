package com.infy.service;

import com.infy.model.Candidate;
import com.infy.model.CandidateReport;
import com.infy.validator.Validator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.logging.LogFactory;

import com.infy.dao.CandidateDAO;
import com.infy.dao.CandidateDAOImpl;
import com.infy.exception.InfyAcademyException;

public class CandidateServiceImpl implements CandidateService {

//	Instance Variable
	private CandidateDAO candidateDAO = new CandidateDAOImpl();

//	Methods
	// can have result as 'P' only if all 3 marks are 50 and above
	@Override
	public String addCandidate(Candidate candidate) throws InfyAcademyException, ConfigurationException {
		PropertiesConfiguration config = new Configurations().properties("configuration.properties");
		Validator validator = new Validator();
		validator.validate(candidate);
		Character tempResult = 'P';
		if (candidate.getMark1() < 50 || candidate.getMark2() < 50 || candidate.getMark3() < 50) {
			tempResult = 'F';
		}
		if (!candidate.getResult().equals(tempResult)) {
			InfyAcademyException e = new InfyAcademyException((String)config.getProperty("Service.INCORRECT_RESULT"));
			LogFactory.getLog(Validator.class).error(e.getMessage(), e);
			throw e;
		}
		return candidateDAO.addCandidate(candidate);
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
		Map<Integer, String> candidateIdGradeMap = new TreeMap<>();
		candidateDAO.getAllCandidates()
					.stream()
					.forEach(candidate -> candidateIdGradeMap.put(candidate.getCandidateId(), this.calculateGrade(candidate)));
		return candidateIdGradeMap;
	}
}
