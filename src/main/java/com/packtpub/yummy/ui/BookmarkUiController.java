package com.packtpub.yummy.ui;

import com.packtpub.yummy.model.Bookmark;
import com.packtpub.yummy.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/bookmark/")
public class BookmarkUiController {
    @Autowired
    BookmarkService bookmarkService;

    @GetMapping("{id}")
    public String details(@PathVariable UUID id, Model model) {
        model.addAttribute("bookmark", bookmarkService.find(id));
        return "bookmark/details";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "{id}", params = "edit=true")
    public String editForm(@PathVariable UUID id, Model model) {
        model.addAttribute("bookmark", bookmarkService.find(id));
        model.addAttribute("isEdit", true);
        return "bookmark/edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String addForm(Model model) {
        model.addAttribute("bookmark", new Bookmark());
        model.addAttribute("isEdit", false);
        return "bookmark/edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("{id}")
    public String saveBookmark(@PathVariable UUID id, @Valid Bookmark bookmark,
                               BindingResult bindingResult, Model model
    ) {
        bookmark.setUuid(id);
        if (bindingResult.hasErrors()){
            model.addAttribute("isEdit", true);
            return "bookmark/edit";
        }
        bookmarkService.update(bookmark);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String addBookmark(@Valid Bookmark bookmark,
                              BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()){
            model.addAttribute("isEdit", false);
            return "bookmark/edit";
        }
        bookmarkService.addBookmark(bookmark);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("{id}/delete")
    public String delete(@PathVariable UUID id) {
        bookmarkService.delete(id);
        return "redirect:/";
    }
}
