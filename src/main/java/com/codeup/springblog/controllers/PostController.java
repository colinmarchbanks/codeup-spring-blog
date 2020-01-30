package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostsRepository;
import com.codeup.springblog.models.User;
import com.codeup.springblog.models.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostsRepository postDao;

    private final UserRepository userDao;

    public PostController(PostsRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
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

    @RequestMapping(path = "/posts/edit")
    public String editPostById(@RequestParam Long id,@RequestParam String title,@RequestParam String body,Model model){
        Post postToUpdate = postDao.getOne(id);
        User user1 = userDao.getOne(1L);
        Post updateTo = new Post(title,body,user1);
        postToUpdate.setTitle(updateTo.getTitle());
        postToUpdate.setBody(updateTo.getBody());
        postDao.save(postToUpdate);
        model.addAttribute("posts",postDao.findAll());
        return "/posts/index";
    }

    @GetMapping(path = "/posts/create")
    public String postsCreateGET(){
        return "/posts/create";
    }

    @GetMapping(path = "/posts/edit/{id}")
    public String postsEditGET(Model model, @PathVariable Long id){
        Post post = postDao.getOne(id);
        model.addAttribute("post",post);
        return "/posts/edit";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.POST)
    public String postCreatePOST(@RequestParam String title, @RequestParam String body){
        User user1 = userDao.getOne(1L);
        Post newPost = new Post(title,body,user1);
        postDao.save(newPost);
        return "/posts/index";
    }

    private void setPostInfoById(Post post,Long id){

    }

//    public static void main(String[] args) {
//        PostController postController = new PostController();
//
//        System.out.println(postController.);
//    }
}
