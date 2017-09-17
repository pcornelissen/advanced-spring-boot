package com.packtpub.yummy.ui;

import com.packtpub.yummy.model.Bookmark;
import com.packtpub.yummy.rest.BookmarksController;
import com.packtpub.yummy.service.BookmarkService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
public class UiControllerTest {
    List<Bookmark> bookmarks = Arrays.asList(
            new Bookmark("Packt publishing",
                    "http://packtpub.com").withUuid(UUID.randomUUID()),
            new Bookmark("orchit GmbH homepage", "http://orchit.de")
                    .withUuid(UUID.randomUUID())
    );

    @Autowired
    MockMvc mvc;

    @MockBean
    BookmarkService bookmarkService;

    @Before
    public void before() {
        when(bookmarkService.findAll()).thenReturn(bookmarks);
    }

    @Test
    public void testFindAllViaMockMvc() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.handler().methodCall(
                        MvcUriComponentsBuilder.on(BookmarksController.class).findAllBookmarks()
                ));
    }

}
