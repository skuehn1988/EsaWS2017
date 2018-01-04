package org.dieschnittstelle.jee.esa.jrs;

import org.dieschnittstelle.jee.esa.entities.crm.StationaryTouchpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/touchpoints")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface ITouchpointCRUDService {
	
	@GET
	public List<StationaryTouchpoint> readAllTouchpoints();

	@GET
	@Path("/{touchpointId}")
	public StationaryTouchpoint readTouchpoint(@PathParam("touchpointId") long id);

	@POST // Create Methode
	public StationaryTouchpoint createTouchpoint(StationaryTouchpoint touchpoint);
	
	@DELETE // Delete Methode
	@Path("/{touchpointId}")
	public boolean deleteTouchpoint(@PathParam("touchpointId") long id);

	@PUT
	@Path("/{touchpointId}")
	public StationaryTouchpoint updateTouchpoint(@PathParam("touchpointId") long id, StationaryTouchpoint touchpoint);
		
	/*
	 * UE JRS1: add a new annotated method for using the updateTouchpoint functionality of TouchpointCRUDExecutor and implement it
	 */
	
}
