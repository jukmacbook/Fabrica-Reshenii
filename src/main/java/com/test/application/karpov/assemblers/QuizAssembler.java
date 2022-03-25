package com.test.application.karpov.assemblers;

import com.test.application.karpov.controllers.admin.QuestionApiController;
import com.test.application.karpov.dto.Quiz;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuizAssembler implements RepresentationModelAssembler<Quiz, EntityModel<Quiz>> {
    @Override
    public EntityModel<Quiz> toModel(Quiz quiz) {
        return EntityModel.of(quiz,
                linkTo(methodOn(QuestionApiController.class).one(quiz.getId())).withSelfRel(),
                linkTo(methodOn(QuestionApiController.class).all()).withRel("questions"));
    }
}
