package com.test.application.karpov.controllers.user;

import com.test.application.karpov.assemblers.UserAssembler;
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
    private final UserAssembler userAssembler;

    @Autowired
    public UserController(UserService userService, UserAssembler userAssembler) {
        this.userService = userService;
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
    public EntityModel<User> one(@PathVariable Long id) {
        User user = userService.findUserById(id);

        return userAssembler.toModel(user);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User newUser, @RequestBody Boolean isAnonymous){
        EntityModel<User> entityModel = userAssembler.toModel(userService.save(newUser, isAnonymous));

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
