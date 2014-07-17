package graph.state;

import java.awt.Color;

public class CorrectModelState implements ModelState {

	private static final long serialVersionUID = 1L;
	public static final String CORRECTSTATUS = "Model Status: Correct";
	public static final Color CORRECTCOLOR = Color.green;
	
	public CorrectModelState() {}
	
	@Override
	public boolean grantAnalysisRights() {
		return true;
	}

	@Override
	public String getStatus() {
		return CORRECTSTATUS;
	}

	@Override
	public Color getStatusColor() {
		return CORRECTCOLOR;
	}
}
