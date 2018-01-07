package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import java.util.List;

import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface ProductCRUDLocal {

    public AbstractProduct createProduct(AbstractProduct prod);

    public List<AbstractProduct> readAllProducts();

    public AbstractProduct updateProduct(AbstractProduct update);

    public AbstractProduct readProduct(long productID);

    public boolean deleteProduct(long productID);

}

