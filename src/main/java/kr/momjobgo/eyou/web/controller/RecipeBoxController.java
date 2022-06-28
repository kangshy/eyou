package kr.momjobgo.eyou.web.controller;

import kr.momjobgo.eyou.web.jpa.entity.RecipeBoxEntity;
import kr.momjobgo.eyou.web.service.RecipeBoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipebox")
public class RecipeBoxController {
    private final RecipeBoxService recipeBoxService;

    public RecipeBoxController(RecipeBoxService recipeBoxService) { this.recipeBoxService = recipeBoxService; }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body( recipeBoxService.getAll() );
    }

    @GetMapping("/id={id}")
    public ResponseEntity<?> getById(@PathVariable Long id) { return ResponseEntity.ok().body( recipeBoxService.getById(id) ); }

    @PostMapping("/name={name}")
    public ResponseEntity<?> insertRecipeBoxName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeBoxService.insertRecipeBoxName(name));
    }

        @PostMapping("/default")
        public ResponseEntity<?> insertRecipeBoxDefault(@RequestBody RecipeBoxEntity recipeBoxEntity){
            return ResponseEntity.status(HttpStatus.CREATED).body(recipeBoxService.insertRecipeBox(recipeBoxEntity));
        }

        @PatchMapping("/{id}?name={name}")
        public ResponseEntity<?> updateRecipeBoxNameById(@PathVariable Long id, @PathVariable String name){
            return ResponseEntity.ok().body(recipeBoxService.updateRecipeBoxNameById(id, name));
        }

        @PatchMapping("/")
        public ResponseEntity<?> updateRecipeBox(@RequestBody RecipeBoxEntity recipeBoxEntity){
            return ResponseEntity.ok().body(recipeBoxService.updateRecipeBox(recipeBoxEntity));
        }

        @DeleteMapping("/id={id}")    // id에 해당되는 데이터 삭제. 없는 id일 경유 500 에러 발생
        public ResponseEntity<?> deleteRecipeBoxById(@PathVariable Long id) {
            return ResponseEntity.ok().body(recipeBoxService.deleteRecipeBoxById(id));
        }

        @DeleteMapping("/all")
        public ResponseEntity<?> deleteRecipeBoxAll() {
            return ResponseEntity.ok().body(recipeBoxService.deleteRecipeBoxAll());
        }

        @GetMapping("/name={name}")
        public ResponseEntity<?> getRecipeBoxByName(@RequestParam String name){
            return ResponseEntity.ok().body(recipeBoxService.findByName(name));
        }

        @GetMapping("/contains?name={name}")
        public ResponseEntity<?> getRecipeBoxByNameContains(@RequestParam String name){
            return ResponseEntity.ok().body(recipeBoxService.findByNameContains(name));
        }

    }
