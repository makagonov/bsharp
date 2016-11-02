import org.junit.Test;
import static org.junit.Assert.*;

public class PrimeSoccerTest {
	
	@Test(timeout=2000)
	public void test0() {
		int skillOfTeamA = 50;
		int skillOfTeamB = 50;
		assertEquals(0.5265618908306351, new PrimeSoccer().getProbability(skillOfTeamA, skillOfTeamB), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test1() {
		int skillOfTeamA = 100;
		int skillOfTeamB = 100;
		assertEquals(0.0, new PrimeSoccer().getProbability(skillOfTeamA, skillOfTeamB), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test2() {
		int skillOfTeamA = 12;
		int skillOfTeamB = 89;
		assertEquals(0.6772047168840167, new PrimeSoccer().getProbability(skillOfTeamA, skillOfTeamB), 1e-9);
	}
}
