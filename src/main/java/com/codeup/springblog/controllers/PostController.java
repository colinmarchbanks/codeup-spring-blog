package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostsRepository postDao;

    private final UserRepository userDao;

    private final TagRepository tagDao;

    private final EmailService emailService;


    public PostController(PostsRepository postDao, UserRepository userDao, TagRepository tagDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
        this.emailService = emailService;
    }



    @GetMapping(path = "/posts")
    public String posts(Model model){
        model.addAttribute("posts",postDao.findAll());
        return "/posts/index";
    }

    @GetMapping(path = "/posts/show")
    public String posts(@RequestParam Long post, Model model){
        model.addAttribute("post",postDao.getOne(post));
        return "/posts/show";
    }

    @RequestMapping(path = "/posts/delete")
    public String deletePostById(@RequestParam long id,Model model){
        postDao.deleteById(id);
        model.addAttribute("posts",postDao.findAll());
        return "/posts/index";
    }

    @RequestMapping(path = "/posts/edit/{id}", method = RequestMethod.POST)
    public String editPostById(@ModelAttribute Post post){
        postDao.save(post);
        return "redirect:/posts";
    }


    @GetMapping(path = "/posts/edit/{id}")
    public String postsEditGET(Model model, @PathVariable Long id){
        Post post = postDao.getOne(id);
        model.addAttribute("post",post);
        return "/posts/edit";
    }

    @GetMapping(path = "/posts/create")
    public String postsCreateGET(Model model){
        model.addAttribute("post", new Post());
        model.addAttribute("tags",tagDao.findAll());
        return "/posts/create";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.POST)
    public String postCreatePOST(@ModelAttribute Post post){
        post.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        postDao.save(post);
        emailService.prepareAndSend(post,"You've created a new post!","You've just created a post titled: " + post.getTitle());
        return "redirect:/posts";
    }

    @RequestMapping(path="/posts/showtag/{id}")
    public String tagShow(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagDao.getOne(id));
        return "/posts/showtag";
    }
//    public static void main(String[] args) {
//        PostController postController = new PostController();
//
//        System.out.println(postController.);
//    }
}
