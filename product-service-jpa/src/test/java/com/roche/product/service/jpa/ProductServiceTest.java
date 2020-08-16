package com.roche.product.service.jpa;

import com.roche.product.service.api.ProductService;
import com.roche.product.service.api.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProductServiceJpaConfTest.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    @Transactional
    public void testCreateProductSimple() {

        double price = 12.23;
        String product0Name = "My Product";
        Product product0 = productService.create(product0Name, price);
        assertEquals(product0.getName(), product0Name);
        assertTrue(product0.getPrice() - price == 0);

        Product product0a = productService.get(product0.getId());
        assertNotNull(product0a);

        assertEquals(product0, product0a);
    }

    @Test
    @Transactional
    public void testCreateInvalid() {

        try {
            productService.create(null, null);
            fail("A product may not be created without a name and a price");
        } catch (Exception e) {

        }

    }

    @Test
    @Transactional
    public void testList() {

        List<? extends Product> products0 = productService.list();
        assertTrue(products0.isEmpty());

        Product product0 = productService.create("My Product", 12.23);

        List<? extends Product> products1 = productService.list();
        assertEquals(products1.size(), 1);
        assertTrue(products1.contains(product0));

    }

    @Test
    @Transactional
    public void testSimpleDelete() {

        List<? extends Product> products0 = productService.list();
        assertTrue(products0.isEmpty());

        Product product0 = productService.create("My Product", 12.23);

        List<? extends Product> products1 = productService.list();
        assertEquals(products1.size(), 1);

        productService.delete(product0);

        try {
            productService.get(product0.getId());
            fail("deleted products cannot be fetched");
        } catch (Exception e) {

        }
    }

    @Test
    @Transactional
    public void testExtendedDelete() {

        List<? extends Product> products0 = productService.list();
        assertTrue(products0.isEmpty());

        Product product0 = productService.create("My Product", 12.23);

        List<? extends Product> products1 = productService.list();
        assertEquals(products1.size(), 1);
        assertTrue((products1.contains(product0)));

        productService.delete(product0);

        List<? extends Product> products2 = productService.list();
        assertTrue(products2.isEmpty());
        assertFalse(products2.contains(product0));

    }

    @Test
    @Transactional
    public void testSimpleUpdate() {

        List<? extends Product> products0 = productService.list();
        assertTrue(products0.isEmpty());

        Product product0 = productService.create("My Product", 12.23);

        List<? extends Product> products1 = productService.list();
        assertEquals(products1.size(), 1);
        assertTrue((products1.contains(product0)));

        Product product1 = productService.update(modifyProduct(product0, "A new name", 0.99));

        List<? extends Product> products2 = productService.list();
        assertEquals(products2.size(), 1);
        assertTrue((products2.contains(product1)));

    }

    @Test
    @Transactional
    public void testUpdateDeleted() {

        List<? extends Product> products0 = productService.list();
        assertTrue(products0.isEmpty());

        Product product0 = productService.create("My Product", 12.23);

        productService.delete(product0);

        List<? extends Product> products1 = productService.list();
        assertTrue(products1.isEmpty());

        try {
            productService.update(modifyProduct(product0, "Another Name", 0.99));
            fail("A deleted product cannot be updated");
        } catch (RuntimeException e) {

        }

    }

    private Product modifyProduct(Product product0, final String name, final double price) {
        return new Product() {
            @Override
            public Long getId() {
                return product0.getId();
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public Double getPrice() {
                return price;
            }

            @Override
            public Date getCreatedAt() {
                return product0.getCreatedAt();
            }
        };
    }


}
