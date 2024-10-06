package ru.netology.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {

    PostResponse postResponse(Post post);

    List<PostResponse> toPostResponseList(List<Post> posts);
}
