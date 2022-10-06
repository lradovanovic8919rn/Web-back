package rs.raf.demo.resources;

import rs.raf.demo.entities.Category;
import rs.raf.demo.services.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> allCategory() {
        return this.categoryService.allCategory();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category findCategory(@PathParam("name") String name) {
        return this.categoryService.findCategory(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category addCategory(Category category) {
        return this.categoryService.addCategory(category);
    }

    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteCategory(@PathParam("name") String name) {
        return this.categoryService.deleteCategory(name);
    }

    @PUT
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category updateCategory(Category category, @PathParam("name") String name) {
        return this.categoryService.updateCategory(category, name);
    }

}
