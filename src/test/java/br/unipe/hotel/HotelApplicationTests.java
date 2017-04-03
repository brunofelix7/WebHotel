package br.unipe.hotel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.unipe.hotel.HotelApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HotelApplication.class)
@WebAppConfiguration
public class HotelApplicationTests {

	@Test
	public void contextLoads() {
	}

}
