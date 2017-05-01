package com.packtpub.yummy.config;

import com.packtpub.yummy.model.Bookmark;
import com.packtpub.yummy.service.BookmarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataInitialization implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(DataInitialization.class);
    @Autowired
    BookmarkService bookmarkService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        bookmarkService.addBookmark(new Bookmark("Packt publishing", "http://packtpub.com"));
        bookmarkService.addBookmark(new Bookmark("orchit GmbH", "http://orchit.de"));
    }
}