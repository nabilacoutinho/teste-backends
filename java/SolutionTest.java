import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class SolutionTest {

	@Test
	public void processMessages_WhenEmptyInput_ThenReturnEmptyString1() {
		String expectedOutput = "";

		List<String> input = Collections.emptyList();
		String output = Solution.processMessages(input);
		
		assertEquals(expectedOutput, output);
	}

	@Test
	public void processMessages_WhenEmptyInput_ThenReturnEmptyString() {
		String expectedOutput = "";

		List<String> input = Collections.emptyList();
		String output = Solution.processMessages(input);
		
		assertEquals(expectedOutput, output);
	}
	
}
