package mk.ukim.finki.wp.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.wp.emtlab.model.enums.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/category")
@Tag(name = "Category API", description = "Endpoint for listing categories")
public class CategoryController {
    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieves a list of all available categories")
    public List<Category> getCategories() {
        return Arrays.stream(Category.values()).collect(Collectors.toList());
    }
}
