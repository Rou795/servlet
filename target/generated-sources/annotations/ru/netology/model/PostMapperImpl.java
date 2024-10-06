package ru.netology.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-06T17:18:28+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 16.0.2 (Amazon.com Inc.)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostResponse postResponse(Post post) {
        if ( post == null ) {
            return null;
        }

        long id = 0L;
        String content = null;

        id = post.getId();
        content = post.getContent();

        PostResponse postResponse = new PostResponse( id, content );

        return postResponse;
    }

    @Override
    public List<PostResponse> toPostResponseList(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostResponse> list = new ArrayList<PostResponse>( posts.size() );
        for ( Post post : posts ) {
            list.add( postResponse( post ) );
        }

        return list;
    }
}
