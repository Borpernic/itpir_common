package ru.lab729.itpir.web;

import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = {"classpath:spring/spring-app.xml", "classpath:spring/mock.xml"})
class InMemoryAdminRestControllerSpringTest {

 /*   @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository.init();
    }

    @Test
    void testDelete() {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        assertEquals(users.size(), 1);
        assertEquals(users.iterator().next(), ADMIN);
    }

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(10));
    }*/
}
