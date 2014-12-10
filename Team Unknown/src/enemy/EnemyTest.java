package enemy;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;

import map.*;

public class EnemyTest {

	@Test
	public void randomTests(){
		Map m = new Map();
		Enemy g = new Enemy1(1,m);
		Point p = new Point(0, 12);
		assertEquals(g.getHealth(), 100);
		assertEquals(g.getPoints(), 10);
		assertEquals(g.getCurrent(), p);
		g.moveToNext();
		Point p2 = new Point(1, 12);
		assertEquals(g.getCurrent(), p2);
		g.doDamage(100);
		assertEquals(g.getHealth(), 0);
		assertTrue(g.isDead());
				
		Enemy h = new Enemy1(2,m);
		assertEquals(h.getHealth(), 200);
		assertEquals(h.getPoints(), 20);

		Enemy a = new Enemy2(1,m);
		assertEquals(a.getHealth(), 100);
		a.doDamage(10);
		assertEquals(a.getHealth(), 99);
	}
	
}
