package cespi.unlp.edu.ar.SistemaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(true)
class SistemaDeEstacionamientoApplicationTests {

	//@Autowired
	//SistemaDeEstacionamientoService service;

	@Test
	void contextLoads() {
	}
	/*@Test
	void testCreationAndGetMotorist(){
		Automovilista automovilista = this.service.createAutomovilista("Juan Perez", "jperez", "1234", "jperez@gmail.com", dob1);
		assertNotNull(automovilista.getId());
		assertEquals(0, client.getScore());
		assertEquals("2213334444", client.getEmail());
	}*/


}
