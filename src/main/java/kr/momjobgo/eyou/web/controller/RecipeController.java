package kr.momjobgo.eyou.web.controller;

import kr.momjobgo.eyou.web.dto.RecipeRequest;
import kr.momjobgo.eyou.web.service.RecipeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Recipe")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
     }

    @GetMapping("/api/filter")
    public ResponseEntity<?> getFilterList(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "sort", required = false) Long sort,
            @RequestParam(value = "period", required = false) Long period,
            @RequestParam(value = "timeTakenId", required = false) Long timeTakenId,
            @RequestParam(value = "Ids", required = false) String ids) {
                List<Long> Ids = new ArrayList<>();
                if (ids != null) {
                    String[] arr = ids.split(",");
                    for (int i = 0; i < arr.length; i++) {
                        Ids.add(Long.parseLong(arr[i]));
                    }
                }
                Long userId = UserManager.getUser().getId();
                System.out.println("===> user ID : "+ userId);
                return ResponseEntity.ok().body( recipeService.findByFilter(PageRequest.of(page, 2), userId, sort, period, timeTakenId, Ids) );
            }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body( recipeService.getAll() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) { return ResponseEntity.ok().body( recipeService.getById(id) ); }

    @PostMapping("/write")
    public ResponseEntity<?> writeRecipe(HttpServletRequest req, @RequestBody RecipeRequest request) {
        return ResponseEntity.ok().body(recipeService.write(req, request));
    }

    @GetMapping("/contents={contentsId}")
    public ResponseEntity<?> getByContentsId(@PathVariable Long contentsId) { return ResponseEntity.ok().body( recipeService.getByContentsId(contentsId) ); }
    
    @PatchMapping("/updateOpen={id}")
    public ResponseEntity<?> updateOpen(Long id) {
        return ResponseEntity.ok().body(recipeService.updateOpen(id));
    }
}
