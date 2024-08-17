package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

// Stub
public class PostRepository {

  List<Post> list = new CopyOnWriteArrayList<>(new ArrayList<>());
  AtomicInteger counter = new AtomicInteger(0);

  public List<Post> all() { return list; }

  public Optional<Post> getById(long id) {
    return list.stream().filter(post -> post.getId() == id).findFirst();
  }

  public Post save(Post post) {
    int index = counter.incrementAndGet();
    post.setId(index);
    list.add(post);
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
    if (this.getById(id).isPresent()) {
      list.remove(this.getById(id).get());
    }
  }
}
