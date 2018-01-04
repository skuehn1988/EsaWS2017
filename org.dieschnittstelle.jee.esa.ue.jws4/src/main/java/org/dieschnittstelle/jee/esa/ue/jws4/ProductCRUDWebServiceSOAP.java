package org.dieschnittstelle.jee.esa.ue.jws4;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;

/*
 * UE JWS4: machen Sie die Funktionalitaet dieser Klasse als Web Service verfuegbar und verwenden Sie fuer
 * die Umsetzung der beiden Methoden die Instanz von GenericCRUDExecutor<AbstractProduct>,
 * die Sie aus dem ServletContext auslesen koennen
 */
@WebService(targetNamespace = "http://dieschnittstelle.org/jee/esa/jws", serviceName = "ProductCRUDWebService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ProductCRUDWebServiceSOAP {

	@Resource
	private WebServiceContext wscontext;

	public List<AbstractProduct> readAllProducts() {
		// we obtain the servlet context from the wscontext
		ServletContext ctx = (ServletContext) wscontext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx.getAttribute("productCRUD");
		return productCRUD.readAllObjects();
	}

	public AbstractProduct createProduct(AbstractProduct product) {
		// we obtain the servlet context from the wscontext
		ServletContext ctx = (ServletContext) wscontext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx.getAttribute("productCRUD");
		return productCRUD.createObject(product);
	}

	public AbstractProduct updateProduct(AbstractProduct update) {
		// we obtain the servlet context from the wscontext
		ServletContext ctx = (ServletContext) wscontext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx.getAttribute("productCRUD");
		return productCRUD.updateObject(update);
	}

	public AbstractProduct readProduct(long id) {

		ServletContext ctx = (ServletContext) wscontext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx.getAttribute("productCRUD");
		return productCRUD.readObject(id);
	}
	public boolean deleteProduct(long id) {

		ServletContext ctx = (ServletContext) wscontext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx.getAttribute("productCRUD");
		return productCRUD.deleteObject(id);
	}
}