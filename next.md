# COMING 

MAKE a parent table that students belong to a classroom

TEST controller endpoint


```` java
@SpringBootTest
class StudentControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRequest() throws Exception {
        mockMvc.perform(get("/your-endpoint"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.key").value("expectedValue"));
    }

}

````

