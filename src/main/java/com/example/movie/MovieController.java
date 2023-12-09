package com.example.movie;

import com.example.movie.MovieServiceImpl;
import com.example.movie.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String movielist(Model model) {
        model.addAttribute("list", movieService.getMovieList());
        return "list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPost() {
        return "addpostform";
    }

    @RequestMapping(value = "addok", method = RequestMethod.POST)
    public String addPostOk(MovieVO vo) {
        if(movieService.insertMovie(vo) == 0) {
            System.out.println("데이터 추가 실패");
        }
        else {
            System.out.println("데이터 추가 성공!!!");
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/editform/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") int id, Model model) {
        MovieVO movieVO = movieService.getMovie(id);
        model.addAttribute("u", movieVO);
        return "editform";
    }

    @RequestMapping(value = "/editok", method = RequestMethod.POST)
    public String editPostOk(MovieVO vo) {
        if(movieService.updateMovie(vo) == 0) {
            System.out.println("데이터 수정 실패");
        }
        else {
            System.out.println("데이터 수정 성공!!!");
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/deleteok/{id}", method = RequestMethod.GET)
    public String deletePostOk(@PathVariable("id") int id) {
        if(movieService.deleteMovie(id) == 0) {
            System.out.println("데이터 삭제 실패");
        }
        else {
            System.out.println("데이터 삭제 성공!!!");
        }
        return "redirect:../list";
    }

}
