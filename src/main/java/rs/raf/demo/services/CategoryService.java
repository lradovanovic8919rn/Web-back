package rs.raf.demo.services;

import rs.raf.demo.entities.Category;
import rs.raf.demo.repositories.category.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    public List<Category> allCategory() {
        return this.categoryRepository.allCategory();
    }

    public Category findCategory(String name) {
        return this.categoryRepository.findCategory(name);
    }

    public Category addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

    public String deleteCategory(String name) {
        return this.categoryRepository.deleteCategory(name);
    }

    public Category updateCategory(Category category, String name) {
        return this.categoryRepository.updateCategory(category, name);
    }

}
