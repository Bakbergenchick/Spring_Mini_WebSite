package com.spring.bitlab_spring.service;

import com.spring.bitlab_spring.entities.Category;
import com.spring.bitlab_spring.entities.Countries;
import com.spring.bitlab_spring.entities.ShopItems;
import com.spring.bitlab_spring.repository.CategroyRepo;
import com.spring.bitlab_spring.repository.CountryRepo;
import com.spring.bitlab_spring.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemServicempl implements ItemService{

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CategroyRepo categoryRepo;

    @Override
    public void mergeItem(ShopItems shopItem) {
        itemRepo.save(shopItem);
    }

    @Override
    public List<ShopItems> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public ShopItems getById(Long id) {
        return itemRepo.getOne(id);
    }

    @Override
    public void deletById(Long id) {
        itemRepo.deleteById(id);
    }

    @Override
    public List<Countries> getAllCountries() {
        return countryRepo.findAll();
    }

    @Override
    public Countries getCountryById(Long id) {
        return countryRepo.getOne(id);
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepo.deleteById(id);
    }

    @Override
    public void mergeCountry(Countries country) {
        countryRepo.save(country);
    }



    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.getOne(id);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public void mergeCategroy(Category category) {
        categoryRepo.save(category);
    }

}
