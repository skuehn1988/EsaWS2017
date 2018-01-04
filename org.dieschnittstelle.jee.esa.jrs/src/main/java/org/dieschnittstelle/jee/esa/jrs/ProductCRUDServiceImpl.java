package org.dieschnittstelle.jee.esa.jrs;

import java.util.List;
import org.apache.log4j.Logger;

import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;

import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;    // ??
import org.dieschnittstelle.jee.esa.entities.crm.StationaryTouchpoint; //??

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;

import static org.dieschnittstelle.jee.esa.utils.Utils.show;

/*
UE JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	protected static Logger logger = Logger.getLogger(ProductCRUDServiceImpl.class);

	private GenericCRUDExecutor<AbstractProduct> productCRUD;

	public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {

		logger.info("<constructor>: " + servletContext + "/" + request);
		show("<constructor>");

		this.productCRUD = (GenericCRUDExecutor<AbstractProduct>)servletContext.getAttribute("productCRUD");

		logger.debug("read out the productCRUD from the servlet context: " + this.productCRUD);
	}

	@Override
	public AbstractProduct createProduct(AbstractProduct prod) {
		show("createProduct");
		return (AbstractProduct) this.productCRUD.createObject(prod);
	}

	@Override
	public List<AbstractProduct> readAllProducts() {
		// TODO Auto-generated method stub
		show("readAllProduct");
		return (List) this.productCRUD.readAllObjects();
	}

	@Override
	public AbstractProduct readProduct(long id) {
		// TODO Auto-generated method stub
		show("readProduct");
		return (AbstractProduct) this.productCRUD.readObject(id);
	}

	@Override
	public AbstractProduct updateProduct(long id, AbstractProduct product) {
		// TODO Auto-generated method stub
		show("updateProduct");
		return (AbstractProduct) this.productCRUD.updateObject(product);
	}

	@Override
	public boolean deleteProduct(long id) {
		// TODO Auto-generated method stub
		show("deleteProduct");
		return this.productCRUD.deleteObject(id);
	}

	
}
