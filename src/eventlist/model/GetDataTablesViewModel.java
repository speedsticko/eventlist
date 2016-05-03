package eventlist.model;

import java.util.Date;
import java.util.List;

public class GetDataTablesViewModel {

	private String sEcho; 
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private Object[] aaData;
	
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public Object[] getAaData() {
		return aaData;
	}
	public void setAaData(Object[] aaData) {
		this.aaData = aaData;
	}
}
