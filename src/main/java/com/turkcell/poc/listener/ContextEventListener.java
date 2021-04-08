package com.turkcell.poc.listener;

import com.turkcell.poc.document.Menu;
import com.turkcell.poc.document.Product;
import com.turkcell.poc.service.MenuService;
import com.turkcell.poc.service.ProductService;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

@Component
public class ContextEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ContextEventListener.class);
    
    @Autowired
    private MenuService menuService;

    @Autowired
    private ProductService productService;

    @Order(1)
    @EventListener
    public void handleContextRefreshEvent(ContextStartedEvent ctxStartEvt) {
        if (!menuService.getMenuById(1L).blockOptional().isPresent()) {
            for (int i = 1; i < 20; i++) {
                menuService.createMenu(new Menu((long) i, " Menu : " + i))
                        .subscribeOn(Schedulers.parallel())
                        .subscribe(menu -> logger.info(menu.getName() + " created"));
            }
        }

        if (!productService.getProductById(1L).blockOptional().isPresent()) {
            for (int i = 1; i < 1001; i++) {

                productService.createProduct(prepareProduct(i))
                        .subscribeOn(Schedulers.parallel())
                        .subscribe(product -> logger.info(product.getMobileNumber()+ " created"));
            }
        }    
    }
    
    private Product prepareProduct(int i) {
        Product product = new Product();
        product.setId((long) i);
        product.setLine("Hat");
        product.setLineType("Hat Tipi");
        product.setMobileNumber("0532 555 " + ThreadLocalRandom.current().nextInt(10000, 100000));
        product.setShortNumber(ThreadLocalRandom.current().nextInt(10000, 100000));
        product.setUsername("Test User : " + i);
        product.setPaymentType("Ödeme Tipi");
        return product;
    }
}


