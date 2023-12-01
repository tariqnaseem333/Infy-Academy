package com.infy.validator;

import java.time.LocalDate;

import com.infy.model.Candidate;

public class Validator {
	//calls the validation methods for individual inputs
	//if any method returns false, then the String value correspondingly
	public String validate(Candidate candidate) {
		if( !this.isValidCandidateId( candidate.getCandidateId() ) ) {
			return "The entered candidate ID is invalid.";
		} else if( !this.isValidCandidateName( candidate.getCandidateName() ) ) {
			return "The entered candidate name is invalid.";
		} else if( !this.isValidExamMarks( candidate ) ) {
			return "The entered exam marks are invalid.";
		} else if( !this.isValidResult( candidate.getResult() ) ) {
			return "The entered result is invalid.";
		} else if( !this.isValidDepartment( candidate.getDepartment() ) ) {
			return "The entered Department name is invalid.";
		} else if( !this.isValidExamDate( candidate.getExamDate() ) ) {
			return "The entered Exam Date is invalid.";
		}
		return "Successfully Validated";
	}	

	// The entered candidate name should contain only alphabets. Cannot have
	// special characters and only spaces
	public Boolean isValidCandidateName(String candidateName) {
		if( candidateName.matches("[A-Za-z]+") ) {
			return true;
		}
		return false;
	}

	// The entered candidate ID should be of size 5
	public Boolean isValidCandidateId(Integer candidateId) {
		if( candidateId.toString().matches("\\d{5}") ) {
			return true;
		}
		return false;
	}

	// The entered Department name should be one among the given departments
	// (ECE, CSE, IT, EEE)
	public Boolean isValidDepartment(String department) {
		if( department.equals("ECE") || department.equals("CSE")
			|| department.equals("IT") || department.equals("EEE") ) {
			return true;
		}
		return false;
	}

	// exam date cannot be today or after todays date
	public Boolean isValidExamDate(LocalDate examDate) {
//		Another Way: LocalDate.now().compareTo(examDate) >= 0
		if( examDate.isBefore(LocalDate.now()) ) {
			return true;
		}  
		return false;
	}
	
	//Checking if marks are not equal to "0"
	public Boolean isValidExamMarks(Candidate candidateTO) {
		if( candidateTO.getMark1() > 0 && candidateTO.getMark2() > 0
			&& candidateTO.getMark3() > 0)  {
			return true;
		}
		return false;
	}
	
	//Checking if result set is either 'P' or 'F' only
	public Boolean isValidResult(Character result) {
		if( result.equals('P') || result.equals('F')) {
			return true;
		}
		return false;
	}
}
