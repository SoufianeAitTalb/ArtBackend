package fr.intellcap.artproject.services;



import fr.intellcap.artproject.dto.CategoryDTO;
import fr.intellcap.artproject.entities.Category;


import java.util.List;

public interface CategoryService {

    Category addNewCategory(CategoryDTO categoryDto);
    Category updateCategory(Long id, CategoryDTO categoryDTO);
    Category updateData(Category category, CategoryDTO categoryDto);
    void deleteCategory(Long id);
    List<CategoryDTO> listCategoriesDTO();
    List<Category> listCategories();

    Category loadCategoryById(Long id);
    CategoryDTO loadCategoryByCategoryId(Long id);


}
