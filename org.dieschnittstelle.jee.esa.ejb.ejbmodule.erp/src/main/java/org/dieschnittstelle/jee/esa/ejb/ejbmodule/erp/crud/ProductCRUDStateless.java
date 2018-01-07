package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductCRUDStateless implements ProductCRUDRemote, ProductCRUDLocal {

    @PersistenceContext(unitName = "erp_PU")
    private EntityManager em;

    @Override
    public AbstractProduct createProduct(AbstractProduct prod) {
        em.persist(prod);
        return prod;
    }

    @Override
    public List<AbstractProduct> readAllProducts() {
        return this.em.createQuery("FROM AbstractProduct").getResultList();
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update) {
        return this.em.merge(update);
    }

    @Override
    public AbstractProduct readProduct(long productID) {
        return this.em.find(AbstractProduct.class, productID);
    }

    @Override
    public boolean deleteProduct(long productID) {
        this.em.remove(this.readProduct(productID));
        return true;
    }
}
