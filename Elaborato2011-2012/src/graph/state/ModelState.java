package graph.state;

import java.awt.Color;
import java.io.Serializable;

public interface ModelState extends Serializable {

	 public boolean grantAnalysisRights();
	 public String getStatus();
	 public Color getStatusColor();
}
