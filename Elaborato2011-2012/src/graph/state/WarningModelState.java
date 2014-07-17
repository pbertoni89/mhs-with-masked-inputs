package graph.state;

import java.awt.Color;

public class WarningModelState implements ModelState {

	private static final long serialVersionUID = 1L;
	public static final String WARNINGSTATUS = "Model Status: Warning";
	public static final Color WARNINGCOLOR = Color.yellow;
	
	public WarningModelState() {}
	
	@Override
	public boolean grantAnalysisRights() {
		return false;
	}

	@Override
	public String getStatus() {
		return WARNINGSTATUS;
	}

	@Override
	public Color getStatusColor() {
		return WARNINGCOLOR;
	}
}
