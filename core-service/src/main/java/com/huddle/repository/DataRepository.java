package com.huddle.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataRepository {

    @Autowired
    @Qualifier("h2JdbcTemplate")
    private JdbcTemplate jdbcTemplate;
}
