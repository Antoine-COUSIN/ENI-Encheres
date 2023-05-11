package bll;

import java.util.ArrayList;
import java.util.List;

public class BidBLLException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private List<Integer> codeErreur;
	
	public BidBLLException() {
		codeErreur = new ArrayList<>();
	}
	
	public void addError(int code) {
		codeErreur.add(code);
	}
	
	public List<Integer> getErrors() {
		return codeErreur;
	}

}
