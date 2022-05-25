package vn.mvpt.md2.exam.service;


import vn.mvpt.md2.exam.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void add(Product newProduct);

    void addProducts(List<Product> newProducts);

    void update(Product newProduct);

    Product findById(Long id);

    Product findByName(String title);

    boolean exist(Long id);

    boolean existByName(String name);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<Product> findAllOrderByPriceASC();

    List<Product> findAllOrderByPriceDESC();
}
