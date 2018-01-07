package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.PointOfSale;
import org.dieschnittstelle.jee.esa.entities.erp.ProductAtPosPK;
import org.dieschnittstelle.jee.esa.entities.erp.StockItem;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StockItemCRUDStateless implements StockItemCRUDLocal {

    @PersistenceContext(unitName = "erp_PU")
    private EntityManager em;

    @Override
    public StockItem createStockItem(StockItem item) {
        return this.em.merge(item);
    }

    @Override
    public StockItem readStockItem(IndividualisedProductItem prod, PointOfSale pos) {
        return this.em.find(StockItem.class, new ProductAtPosPK(prod, pos));
    }

    @Override
    public StockItem updateStockItem(StockItem item) {
        return this.em.merge(item);
    }

    @Override
    public List<StockItem> readAllStockItems() {
        return this.em.createQuery("FROM StockItem").getResultList();
    }

    @Override
    public List<StockItem> readStockItemsForProduct(IndividualisedProductItem prod) {
        Query query = this.em.createQuery("SELECT s FROM StockItem s WHERE s.product = " + prod.getId());
        return query.getResultList();
    }

    @Override
    public List<StockItem> readStockItemsForPointOfSale(PointOfSale pos) {
        Query query = this.em.createQuery("SELECT s FROM StockItem s WHERE s.pos = " + pos.getId());
        return query.getResultList();
    }
}

