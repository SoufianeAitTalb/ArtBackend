package fr.intellcap.artproject.controller;

import fr.intellcap.artproject.dto.CategoryDTO;
import fr.intellcap.artproject.entities.Category;
import fr.intellcap.artproject.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("**")
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Category> getAllCategories(){
        return this.categoryService.listCategories();
    }
    @GetMapping("/dto")
    public List<CategoryDTO> getAllCategoriesDTO(){
        return this.categoryService.listCategoriesDTO();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return this.categoryService.loadCategoryByCategoryId(id);
    }

   
    @PostMapping("/add-category")
    public ResponseEntity<Map<String, String>> addNewCategory(@RequestBody CategoryDTO categoryDto) {
    Map<String, String> response = new HashMap<>();



    if (this.categoryService.addNewCategory(categoryDto) != null) {
        response.put("status", "success");
        response.put("message", "La catégorie a été ajouté avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de l'ajout de catégorie");
    return ResponseEntity.badRequest().body(response);
}


   
    @PostMapping("/update-category/{id}")
    
    public ResponseEntity<Map<String, String>> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDto) {
    Map<String, String> response = new HashMap<>();


    if(this.categoryService.updateCategory(id, categoryDto) != null ) {
        response.put("status", "success");
        response.put("message", "La catégorie a été modifié avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de la modification de catégorie");
    return ResponseEntity.badRequest().body(response);
}

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long id){
        this.categoryService.deleteCategory(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "La catégorie a été supprimé avec succès");
        return ResponseEntity.ok(response);

    }


}
