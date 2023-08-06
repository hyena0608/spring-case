package com.hyena.springpageable.domain.post.controller;

import com.hyena.springpageable.config.RestdocsConfig;
import com.hyena.springpageable.domain.member.domain.Member;
import com.hyena.springpageable.domain.post.domain.Post;
import com.hyena.springpageable.domain.post.dto.PostResponse;
import com.hyena.springpageable.domain.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.payload.JsonFieldType.BOOLEAN;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PostController.class)
class PostControllerTest extends RestdocsConfig {

    @MockBean
    private PostService postService;

    final Member member = new Member("홍길동");

    final List<PostResponse> postStockResponse = List.of(
            PostResponse.of(new Post("안녕1", "안녕 애들아1", "잡담", member)),
            PostResponse.of(new Post("안녕2", "안녕 애들아2", "잡담", member)),
            PostResponse.of(new Post("안녕3", "안녕 애들아3", "잡담", member))
    );

    @Test
    void v1() throws Exception {
        when(postService.findPostsByCategory(anyString(), any()))
                .thenReturn(new PageImpl<>(postStockResponse, PageRequest.of(10, 10), 10));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/posts")
                        .queryParam("category", "잡담")
                        .queryParam("page", "10")
                        .queryParam("size", "10")
                        .contentType(APPLICATION_JSON)
                        .characterEncoding(UTF_8))
                .andDo(print())
                .andDo(restDocs.document(
                        queryParameters(
                                parameterWithName("category").description(""),
                                parameterWithName("page").description(""),
                                parameterWithName("size").description("")
                        ),
                        responseFields(
                                fieldWithPath("stock.[].title").type(STRING).description(""),
                                fieldWithPath("stock.[].content").type(STRING).description(""),
                                fieldWithPath("stock.[].category").type(STRING).description(""),
                                fieldWithPath("stock.[].member.id").type(STRING).description("").optional(),
                                fieldWithPath("stock.[].member.name").type(STRING).description(""),
                                fieldWithPath("pageableCustom.first").type(BOOLEAN).description(""),
                                fieldWithPath("pageableCustom.last").type(BOOLEAN).description(""),
                                fieldWithPath("pageableCustom.hasNext").type(BOOLEAN).description(""),
                                fieldWithPath("pageableCustom.totalPages").type(NUMBER).description(""),
                                fieldWithPath("pageableCustom.totalElements").type(NUMBER).description(""),
                                fieldWithPath("pageableCustom.page").type(NUMBER).description(""),
                                fieldWithPath("pageableCustom.size").type(NUMBER).description("")
                        )
                ));
    }
}
