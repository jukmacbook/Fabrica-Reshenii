package com.test.application.karpov.controllers.user;

import com.test.application.karpov.assemblers.QuizAssembler;
import com.test.application.karpov.assemblers.UserAssembler;
import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.dto.User;
import com.test.application.karpov.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final QuizAssembler quizAssembler;
    private final UserAssembler userAssembler;

    @Autowired
    public UserController(UserService userService, QuizAssembler quizAssembler, UserAssembler userAssembler) {
        this.userService = userService;
        this.quizAssembler = quizAssembler;
        this.userAssembler = userAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> all(){
        List<EntityModel<User>> questions = userService.findAll().stream()
                .map(userAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(questions,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping(value = "/{id}")
    public CollectionModel<EntityModel<Quiz>> allQuizzesByUserID(@PathVariable Long id){
        List<EntityModel<Quiz>> questions = userService.findUserById(id).getQuizzes().stream()
                .map(quizAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(questions,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User newUser){
        EntityModel<User> entityModel = userAssembler.toModel(userService.save(newUser));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> delete(@PathVariable Long user_id){
        userService.delete(user_id);

        return ResponseEntity.noContent().build();
    }
}
