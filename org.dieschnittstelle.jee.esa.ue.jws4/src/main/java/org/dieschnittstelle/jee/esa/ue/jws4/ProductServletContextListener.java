package org.dieschnittstelle.jee.esa.ue.jws4;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.ProductType;
import org.apache.log4j.Logger;

/*
 * this listener manages a crud executor for proucts in the same way as the TouchpointsServletContextListener for touchpoints
 */
@WebListener
public class ProductServletContextListener implements ServletContextListener {

	protected static Logger logger = Logger
			.getLogger(ProductServletContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		logger.info("contextDestroyed()");

		// we read out the CRUDExecutor for products and let it store its content
		GenericCRUDExecutor<AbstractProduct> exec = (GenericCRUDExecutor<AbstractProduct>) evt
				.getServletContext().getAttribute("productCRUD");

		logger.info("contextDestroyed(): loaded executor from context: " + exec);

		if (exec == null) {
			logger.warn("contextDestroyed(): no executor found in context. Ignore.");
		} else {
			exec.store();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {

		logger.info("contextInitialised()");

		String rootPath = evt.getServletContext().getRealPath("/");
		
		GenericCRUDExecutor<AbstractProduct> exec = new GenericCRUDExecutor<AbstractProduct>(new File(
				rootPath, "products.data"));

		exec.load();

		if (exec.readAllObjects().size() == 0) {
			IndividualisedProductItem prod1 = new IndividualisedProductItem("Schusterjunge",ProductType.ROLL, 720);
			exec.createObject(prod1);
		}

		evt.getServletContext().setAttribute("productCRUD", exec);
	}

}
