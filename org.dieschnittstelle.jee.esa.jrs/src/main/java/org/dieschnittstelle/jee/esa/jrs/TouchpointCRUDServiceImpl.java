package org.dieschnittstelle.jee.esa.jrs;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;

public class TouchpointCRUDServiceImpl implements ITouchpointCRUDService {
	
	protected static Logger logger = Logger.getLogger(TouchpointCRUDServiceImpl.class);

	private GenericCRUDExecutor<AbstractTouchpoint> touchpointCRUD;

	public TouchpointCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
		logger.info("<constructor>: " + servletContext + "/" + request);
		this.touchpointCRUD = (GenericCRUDExecutor<AbstractTouchpoint>)servletContext.getAttribute("touchpointCRUD");

		logger.debug("read out the touchpointCRUD from the servlet context: " + this.touchpointCRUD);		
	}


	@Override
	public List<StationaryTouchpoint> readAllTouchpoints() {
		return (List) this.touchpointCRUD.readAllObjects();
	}

	@Override
	public StationaryTouchpoint createTouchpoint(StationaryTouchpoint touchpoint) {
		return (StationaryTouchpoint) this.touchpointCRUD.createObject(touchpoint);
	}

	@Override
	public boolean deleteTouchpoint(long id) {
		return this.touchpointCRUD.deleteObject(id);
	}

	@Override
	public StationaryTouchpoint readTouchpoint(long id) {
		return (StationaryTouchpoint) this.touchpointCRUD.readObject(id);
	}

	@Override
	public StationaryTouchpoint updateTouchpoint(long id, StationaryTouchpoint touchpoint) {
		touchpoint.setId(id);
		return (StationaryTouchpoint) this.touchpointCRUD.updateObject(touchpoint);
	}
}
