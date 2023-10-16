package fr.intellcap.artproject.services.impl;

import fr.intellcap.artproject.dto.CategoryDTO;
import fr.intellcap.artproject.entities.Category;

import fr.intellcap.artproject.repositories.CategoryRepo;

import fr.intellcap.artproject.repositories.PaintRepo;
import fr.intellcap.artproject.services.CategoryService;
import javax.persistence.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final PaintRepo paintRepo;


    @Autowired
    public CategoryServiceImp(CategoryRepo categoryRepo, PaintRepo paintRepo){
        this.categoryRepo = categoryRepo;
        this.paintRepo=paintRepo;
    }

    @Transactional
    @Override
    public Category addNewCategory(CategoryDTO categoryDto) {
        Category category = new Category();

       category.setCategoryId(categoryDto.getCategoryId());
       category.setName(categoryDto.getName());

        return this.categoryRepo.save(category);
    }

    @Transactional 
    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDto) {
        Category category = this.categoryRepo.findById(id).orElse(null);

        if(category != null){

            category = this.updateData(category, categoryDto);

            return this.categoryRepo.save(category);
        }
        return null;
    }

    @Override
    public Category updateData(Category category, CategoryDTO categoryDto) {


        category.setCategoryId(categoryDto.getCategoryId());
        category.setName(categoryDto.getName());

        return category;
    }

    @Override
    public void deleteCategory(Long id) {

//        categoryRepo.deleteById(id);
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));

        categoryRepo.delete(category);
    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepo.findAll();
    }

    @Override
    public List<CategoryDTO> listCategoriesDTO() {
        List<Category> categorys = this.categoryRepo.findAll();
        List<CategoryDTO> categorysDTO = categorys.stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
        return categorysDTO; // Return the list of CategoryDTOs
    }

    public CategoryDTO convertToCategoryDTO(Category category) {
         if(category == null){
            return null;
        }
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    @Override
    public Category loadCategoryById(Long id) {
        return this.categoryRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public CategoryDTO loadCategoryByCategoryId(Long id) {
        Category category = this.categoryRepo.findById(id).orElse(null);
        if(category == null){
            return null;
        }
        CategoryDTO categoryDto = new CategoryDTO();


        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }












}
