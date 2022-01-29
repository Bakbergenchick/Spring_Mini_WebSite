package com.spring.bitlab_spring.service;


import com.spring.bitlab_spring.entities.Category;
import com.spring.bitlab_spring.entities.Countries;
import com.spring.bitlab_spring.entities.ShopItems;

import java.util.List;

public interface ItemService {

    // ShopItems
    void mergeItem(ShopItems shopItem);

    List<ShopItems> getAllItems();

    ShopItems getById(Long id);

    void deletById(Long id);

    // Country
    List<Countries> getAllCountries();

    Countries getCountryById(Long id);

    void deleteCountry(Long id);

    void mergeCountry(Countries country);

    // Category
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    void deleteCategory(Long id);

    void mergeCategroy(Category category);

}
