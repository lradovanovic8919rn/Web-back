package rs.raf.demo.repositories.category;

import rs.raf.demo.entities.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> allCategory();

    Category findCategory(String name);

    Category addCategory(Category category);

    String deleteCategory(String name);

    Category updateCategory(Category category, String name);

}
