package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public List<Post> all() {
    return service.all();
  }

  @GetMapping("/{id}")
  public Post getById(@PathVariable long id) throws IOException {
    // TODO: deserialize request & serialize response
    return service.getById(id);
  }

  @PostMapping
  public Post save(@RequestBody Post post) throws IOException {
    return service.save(post);
  }

  @PostMapping("{id}")
  public Post save(@RequestBody Post post, @PathVariable long id, HttpServletResponse response) throws IOException {
    return service.save(post, id);
  }

  @DeleteMapping("{id}")
  public void removeById(@PathVariable long id, HttpServletResponse response) throws IOException {
    // TODO: deserialize request & serialize response
    service.removeById(id);
    String answer = "[{\"result\":\"success\"}]";
    response.getWriter().print(answer);
  }
}
