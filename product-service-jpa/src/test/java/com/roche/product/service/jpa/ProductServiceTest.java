package com.roche.product.service.jpa;

import com.roche.product.service.api.ProductService;
import com.roche.product.service.api.model.Product;
import com.roche.product.service.jpa.conf.ProductServiceJpaConf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProductServiceJpaConf.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    @Transactional
    public void productPersistenceTest() {

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

}
