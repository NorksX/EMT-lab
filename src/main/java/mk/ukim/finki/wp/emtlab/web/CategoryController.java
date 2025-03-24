package mk.ukim.finki.wp.emtlab.web;

import mk.ukim.finki.wp.emtlab.model.enums.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @GetMapping
    public List<Category> getCategories() {
        return Arrays.stream(Category.values()).collect(Collectors.toList());
    }
}
