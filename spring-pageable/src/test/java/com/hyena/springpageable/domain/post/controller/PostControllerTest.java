package com.hyena.springpageable.domain.post.controller;

import com.hyena.springpageable.domain.member.domain.Member;
import com.hyena.springpageable.domain.post.domain.Post;
import com.hyena.springpageable.domain.post.dto.PostResponse;
import com.hyena.springpageable.domain.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    final Member member = new Member("홍길동");

    final List<PostResponse> postStockResponse = List.of(
            PostResponse.of(new Post("안녕1", "안녕 애들아1", "잡담", member)),
            PostResponse.of(new Post("안녕2", "안녕 애들아2", "잡담", member)),
            PostResponse.of(new Post("안녕3", "안녕 애들아3", "잡담", member))
    );

    @Test
    void 게시글_커스텀_페이징_요청_응답_성공() throws Exception {
        // given
        final String category = "잡담";
        final int page = 0;
        final int size = 3;
        final PageRequest pageRequest = PageRequest.of(page, size);
        final Page<PostResponse> postResponse =
                new PageImpl<>(postStockResponse, pageRequest, postStockResponse.size());

        when(postService.findPostsByCategory(category, pageRequest))
                .thenReturn(postResponse);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/posts")
                        .param("category", "잡담")
                        .param("page", "0")
                        .param("size", "3")
                )
                .andDo(print())
                .andExpect(jsonPath("pageableCustom.page").value(1));
    }
}