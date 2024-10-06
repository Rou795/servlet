package ru.netology.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.model.PostMapper;
import ru.netology.model.PostResponse;
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
  private final PostMapper postMapper;

  public PostController(PostService service, PostMapper postMapper) {
    this.service = service;
      this.postMapper = postMapper;
  }

  @GetMapping
  public List<PostResponse> all() {
    List<Post> posts = service.all();
    List<PostResponse> postsResponse = postMapper.toPostResponseList(posts);
    return postsResponse;
  }

  @GetMapping("/{id}")
  public PostResponse getById(@PathVariable long id) throws IOException {
    // TODO: deserialize request & serialize response
    Post post = service.getById(id);
    PostResponse postResponse = postMapper.postResponse(post);
    return postResponse;
    //return service.getById(id);
  }

  @PostMapping
  public PostResponse save(@RequestBody Post post) throws IOException {
    post.setStatus(true);
    Post postNew = service.save(post);
    PostResponse postResponse =  postMapper.postResponse(postNew);
    return postResponse;
  }

  @PostMapping("{id}")
  public PostResponse save(@RequestBody Post post, @PathVariable long id, HttpServletResponse response) throws IOException {
    Post postNew = service.save(post, id);
    postNew.setStatus(true);
    PostResponse postResponse = postMapper.postResponse(postNew);
    return postResponse;
  }

  @DeleteMapping("{id}")
  public void removeById(@PathVariable long id, HttpServletResponse response) throws IOException {
    // TODO: deserialize request & serialize response
    service.removeById(id);
    String answer = "[{\"result\":\"success\"}]";
    response.getWriter().print(answer);
  }
}
