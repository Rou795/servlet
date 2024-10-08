package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostRepositoryStubImpl implements PostRepository {

  List<Post> list = new CopyOnWriteArrayList<>(new ArrayList<>());
  ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
  AtomicInteger counter = new AtomicInteger(0);
  @Override
  public List<Post> all() { return posts.values().stream().filter(Post::isActual).toList(); }

  @Override
  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  @Override
  public Post save(Post post) {
    int index = counter.incrementAndGet();
    post.setId(index);
    posts.put(post.getId(), post);
    //list.add(post);
    return post;
  }

  @Override
  public Post save(Post post, long id) {
    Optional<Post> post1 = this.getById(id);
    if (post1.isPresent()) {
      post1.get().setContent(post.getContent());
    } else {
      throw new NotFoundException("We have not post with this id.");
    }
    return post1.get();
  }

  @Override
  public void removeById(long id) {
    Optional<Post> post = this.getById(id);
    if (post.isPresent()) {
      post.get().setStatus(false);
    } else {
      throw new NotFoundException("We have not post with this id.");
    }
  }
}
