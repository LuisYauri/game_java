package ia.core.entorno.mapa;

import ia.core.agente.impl.AccionDinamica;

public class MoveToAction extends AccionDinamica {
	public static final String ATTRIBUTE_MOVE_TO_LOCATION = "location";

	public MoveToAction(String location) {
		super("moveTo");
		setAttribute(ATTRIBUTE_MOVE_TO_LOCATION, location);
	}

	public String getToLocation() {
		return (String) getAttribute(ATTRIBUTE_MOVE_TO_LOCATION);
	}
}
