package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    public List<Post> all();

    public Optional<Post> getById(long id);

    public Post save(Post post);

    public Post save(Post post, long id);

    public void removeById(long id);
}
