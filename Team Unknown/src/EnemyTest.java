import static org.junit.Assert.*;

import org.junit.Test;

public class EnemyTest {

	@Test
	public void randomTests(){
		Enemy g = new Grunt(1);
		assertEquals(g.getHealth(), 100);
		assertEquals(g.getPoints(), 10);
		g.doDamage(100);
		assertEquals(g.getHealth(), 0);
		
		Enemy h = new Grunt(2);
		assertEquals(h.getHealth(), 200);
		assertEquals(h.getPoints(), 20);

		Enemy a = new Armored(1);
		assertEquals(a.getHealth(), 100);
		a.doDamage(10);
		assertEquals(a.getHealth(), 99);
	}
	
}
