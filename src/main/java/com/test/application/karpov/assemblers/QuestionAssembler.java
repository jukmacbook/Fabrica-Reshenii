package com.test.application.karpov.assemblers;

import com.test.application.karpov.controllers.admin.QuestionApiController;
import com.test.application.karpov.dto.Question;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuestionAssembler implements RepresentationModelAssembler<Question, EntityModel<Question>> {
    @Override
    public EntityModel<Question> toModel(Question question) {
        return EntityModel.of(question,
                linkTo(methodOn(QuestionApiController.class).one(question.getId())).withSelfRel(),
                linkTo(methodOn(QuestionApiController.class).all()).withRel("questions"));
    }
}
