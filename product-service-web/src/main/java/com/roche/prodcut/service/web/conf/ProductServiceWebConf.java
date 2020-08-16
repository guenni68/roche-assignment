package com.roche.prodcut.service.web.conf;

import com.roche.product.service.jpa.conf.ProductServiceJpaConf;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ProductServiceJpaConf.class)
public class ProductServiceWebConf {
}
