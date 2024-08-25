package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class PostRepository {

  List<Post> list = new CopyOnWriteArrayList<>(new ArrayList<>());
  ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
  AtomicInteger counter = new AtomicInteger(0);

  public List<Post> all() { return posts.values().stream().toList(); }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) {
    int index = counter.incrementAndGet();
    post.setId(index);
    posts.put(post.getId(), post);
    //list.add(post);
    return post;
  }

  public Post save(Post post, long id) {
    Optional<Post> post1 = this.getById(id);
    if (post1.isPresent()) {
      post1.get().setContent(post.getContent());
    } else {
      throw new NotFoundException("We have not post with this id.");
    }
    return post1.get();
  }

  public void removeById(long id) {
    Optional<Post> post = this.getById(id);
    if (post.isPresent()) {
      posts.remove(post.get());
    } else {
      throw new NotFoundException("We have not post with this id.");
    }
  }
}
