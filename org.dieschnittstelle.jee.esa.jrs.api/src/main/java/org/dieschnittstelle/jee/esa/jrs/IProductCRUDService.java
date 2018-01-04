package org.dieschnittstelle.jee.esa.jrs;

import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/product")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})


public interface IProductCRUDService {

/*
 * UE JRS2:
 * deklarieren Sie hier Methoden fuer:
 * - die Erstellung eines Produkts
 * - das Auslesen aller Produkte
 * - das Auslesen eines Produkts
 * - die Aktualisierung eines Produkts
 * - das Loeschen eines Produkts
 * und machen Sie diese Methoden mittels JAX-RS Annotationen als WebService verfuegbar
 */

	@GET
	List<AbstractProduct> readAllProducts();

	@GET
	@Path("/{productId}")
	AbstractProduct readProduct(@PathParam("productId") long id);

	@POST
	AbstractProduct createProduct(AbstractProduct product);

	@PUT
	@Path("/{productId}")
	AbstractProduct updateProduct(@PathParam("productId") long id, AbstractProduct product);

	@DELETE
	@Path("/{productId}")
	boolean deleteProduct(@PathParam("productId") long id);


}


/*
 * UE JRS3: aendern Sie Argument- und Rueckgabetypen der Methoden von IndividualisedProductItem auf AbstractProduct

public interface IProductCRUDService {

	public IndividualisedProductItem createProduct(IndividualisedProductItem prod);

	public List<IndividualisedProductItem> readAllProducts();

	public IndividualisedProductItem updateProduct(long id,
												   IndividualisedProductItem update);

	boolean deleteProduct(long id);

	public IndividualisedProductItem readProduct(long id);
			
}
*/

