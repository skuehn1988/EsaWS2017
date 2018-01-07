package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.PointOfSaleCRUDLocal;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.StockItemCRUDLocal;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.PointOfSale;
import org.dieschnittstelle.jee.esa.entities.erp.StockItem;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class StockSystemSingleton implements StockSystemRemote {

    @EJB
    private PointOfSaleCRUDLocal posCRUD;

    @EJB
    private StockItemCRUDLocal siCRUD;

    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem si = siCRUD.readStockItem(product, pos);

        if (si == null) {
            si = new StockItem(product, pos, units);
            siCRUD.createStockItem(si);
        } else {
            si.setUnits(si.getUnits() + units);
            siCRUD.updateStockItem(si);
        }
    }

    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        this.addToStock(product, pointOfSaleId, (units * -1));
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        List<StockItem> stockItems = siCRUD.readStockItemsForPointOfSale(pos);

        List<IndividualisedProductItem> individualisedProductItems = new ArrayList<>();
        for (StockItem stockItem : stockItems) {
            individualisedProductItems.add(stockItem.getProduct());
        }
        return individualisedProductItems;
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        List<PointOfSale> pointOfSales = posCRUD.readAllPointsOfSale();

        List<IndividualisedProductItem> individualisedProductItems = new ArrayList<>();
        for (PointOfSale pointOfSale : pointOfSales) {
            individualisedProductItems.addAll(this.getProductsOnStock(pointOfSale.getId()));
        }
        return individualisedProductItems;
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem si = siCRUD.readStockItem(product, pos);

        if (si == null) {
            return 0;
        } else {
            return si.getUnits();
        }
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        List<StockItem> sis = siCRUD.readStockItemsForProduct(product);
        int units = 0;
        for (StockItem si : sis) {
            units += si.getUnits();
        }
        return units;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        List<StockItem> stockItems = siCRUD.readStockItemsForProduct(product);

        List<Long> posLongs = new ArrayList<>();
        for (StockItem stockItem : stockItems) {
            posLongs.add(stockItem.getPos().getId());
        }
        return posLongs;
    }
}
