package graph.state;

import java.awt.Color;

public class ErrorModelState implements ModelState {

	private static final long serialVersionUID = 1L;
	public static final String ERRORSTATUS = "Model Status: Error";
	public static final Color ERRORCOLOR = Color.red;
	
	public ErrorModelState() {}
	
	@Override
	public boolean grantAnalysisRights() {
		return false;
	}

	@Override
	public String getStatus() {
		return ERRORSTATUS;
	}

	@Override
	public Color getStatusColor() {
		return ERRORCOLOR;
	}
}
