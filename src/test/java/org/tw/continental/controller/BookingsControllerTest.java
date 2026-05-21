package org.tw.continental.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.tw.continental.service.BookingsService;

@SpringBootTest
@AutoConfigureRestTestClient
public class BookingsControllerTest {
    @Autowired
    private RestTestClient client;

    @MockitoBean
    private BookingsService bookingsService;

    @Test
    void shouldAbleToBookAHotel() {
    }
}
