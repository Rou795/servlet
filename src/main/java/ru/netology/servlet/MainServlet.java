package ru.netology.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.config.JavaConfig;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.repository.PostRepositoryStubImpl;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
  private PostController controller;
  private static final String[] urls = {"/api/posts", "/api/posts/\\d+", "/"};

  @Override
  public void init() {
    /*
    final var repository = new PostRepositoryStubImpl();
    final var service = new PostService(repository);
    controller = new PostController(service);
    */
    final var context = new AnnotationConfigApplicationContext(JavaConfig.class);
    controller = context.getBean(PostController.class);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    // если деплоились в root context, то достаточно этого
    try {
      final var path = req.getRequestURI();
      final var method = req.getMethod();
      // primitive routing
      if (HttpMethod.GET.toString().equals(method) && path.equals(urls[0])) {
        controller.all(resp);
        return;
      }
      if (HttpMethod.GET.toString().equals(method) && path.matches(urls[1])) {
        // easy way
        final var id = Long.parseLong(path.substring(path.lastIndexOf(urls[2]) + 1));
        controller.getById(id, resp);
        return;
      }
      if (HttpMethod.POST.toString().equals(method) && path.equals(urls[0])) {
        controller.save(req.getReader(), resp);
        return;
      }
      if (HttpMethod.POST.toString().equals(method) && path.matches(urls[1])) {
        final var id = Long.parseLong(path.substring(path.lastIndexOf(urls[2]) + 1));
        controller.save(req.getReader(), id, resp);
        return;
      }
      if (HttpMethod.DELETE.toString().equals(method) && path.matches(urls[1])) {
        // easy way
        final var id = Long.parseLong(path.substring(path.lastIndexOf(urls[2]) + 1));
        controller.removeById(id, resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}

