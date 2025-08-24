package org.iti;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    
    @Test
    public void testGetMessage() {
        App app = new App();
        assertEquals("Hello from Java App 2!", app.getMessage());
    }
    
    @Test
    public void testAppExists() {
        App app = new App();
        assertNotNull(app);
    }
}
