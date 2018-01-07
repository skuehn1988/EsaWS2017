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

@WebService(targetNamespace = "http://dieschnittstelle.org/jee/esa/jws", serviceName = "ProductCRUDWebService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ProductCRUDWebServiceSOAP {

	@Resource
	private WebServiceContext wscontext;

	private GenericCRUDExecutor<AbstractProduct> productCRUD(){
		ServletContext context = (ServletContext) wscontext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		return (GenericCRUDExecutor<AbstractProduct>) context.getAttribute("productCRUD");
	}

	public List<AbstractProduct> readAllProducts() {
		return this.productCRUD().readAllObjects();
	}

	public AbstractProduct createProduct(AbstractProduct product) {
		return this.productCRUD().createObject(product);
	}

	public AbstractProduct updateProduct(AbstractProduct update) {
		return this.productCRUD().updateObject(update);
	}

	public AbstractProduct readProduct(long id) {
		return this.productCRUD().readObject(id);
	}
	public boolean deleteProduct(long id) {
		return this.productCRUD().deleteObject(id);
	}
}