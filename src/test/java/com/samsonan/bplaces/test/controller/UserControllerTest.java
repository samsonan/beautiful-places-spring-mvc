package com.samsonan.bplaces.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.samsonan.bplaces.config.WebConfig;
import com.samsonan.bplaces.test.config.TestContext;

//http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/unit-testing.html
//http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/integration-testing.html

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//  DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@WebAppConfiguration
@DatabaseSetup("/bplace_user_init.xml")
@ActiveProfiles("junit_test")
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        //Mockito.reset(userServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    /**
     *
     //Unit Test --> Useless
    @Test
    public void findById_UserNotFound_ShouldRender404View() throws Exception {
        when(userServiceMock.findById(1)).thenReturn(null);
 
        mockMvc.perform(get("/users/{id}/update", 1))
                .andExpect(status().isOk())//not 404
                .andExpect(view().name("error/404"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/error/404.jsp"));
 
        verify(userServiceMock, times(1)).findById(ID);
        verifyZeroInteractions(userServiceMock);
    }    
    */
    
    @Test
    @ExpectedDatabase("/bplace_user_init.xml")
    public void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/user_list"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/users/user_list.jsp"))
                .andExpect(model().attribute("userList", hasSize(3)))
				.andExpect(model().attribute("userList",
						hasItem(allOf(hasProperty("id", is(1)), hasProperty("name", is("admin")),
								hasProperty("email", is("admin@email.com")), hasProperty("password", is("p@ss"))))))
				.andExpect(model().attribute("userList",
						hasItem(allOf(hasProperty("id", is(2)), hasProperty("name", is("user_active")),
								hasProperty("email", is("user_active@email.com")), hasProperty("password", is("pass111"))))))
				.andExpect(model().attribute("userList",
						hasItem(allOf(hasProperty("id", is(3)), hasProperty("name", is("user_pending")),
								hasProperty("email", is("user_pending@email.com")), hasProperty("password", is("pass222"))))));
    }

    
    
	
}
