package com.roche.product.service.api.model;

import java.util.Date;

public interface Product {

    Long getId();

    String getName();

    Double getPrice();

    Date getCreatedAt();
}
