package inventory.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class InventoryControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    
    @Test
    void testAddInventory() throws Exception {
        String mockInventoryJson = "{\"brand\":\"Brand Test Name\",\"model\":\"Model Test Name\",\"price\":12345}";
    
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/inventory")
                .accept(MediaType.APPLICATION_JSON).content(mockInventoryJson)
                .contentType(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/inventory/5",
                response.getHeader(HttpHeaders.LOCATION));
        
    }
    
    @Test
    void testGetInventoryById() throws Exception {
        
        testAddInventory();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/inventory/5")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        String expected = "{\"id\":5,\"brand\":\"Brand Test Name\",\"model\":\"Model Test Name\",\"price\":12345}";
        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    void testGetAllInventory() {
    }
    

    
    @Test
    void testUpdateInventory() throws Exception {
    
        testAddInventory();
        String mockApplicationJson = "{\"id\":5,\"brand\":\"Brand Test Name Updated\",\"model\":\"Model Test Name Updated\",\"price\":654321}";
    
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/inventory/5")
                .accept(MediaType.APPLICATION_JSON).content(mockApplicationJson)
                .contentType(MediaType.APPLICATION_JSON);
    
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    
        String expected = "{\"id\":5,\"brand\":\"Brand Test Name Updated\",\"model\":\"Model Test Name Updated\",\"price\":654321}";
        assertEquals(expected, result.getResponse().getContentAsString());
    }
    
    
    @Test
    void deleteInventory() throws Exception {
    
        testAddInventory();
        String mockApplicationJson = "{\"id\":5,\"brand\":\"Brand Test Name Updated\",\"model\":\"Model Test Name Updated\",\"price\":654321}";
    
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/inventory/5")
                .accept(MediaType.APPLICATION_JSON).content(mockApplicationJson)
                .contentType(MediaType.APPLICATION_JSON);
    
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
    
        //Assert status is 204 No Content
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        
    }
}