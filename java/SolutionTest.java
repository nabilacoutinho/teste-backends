import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SolutionTest {

	@Test
	public void testProcessMessages_WhenEmptyInput_ThenReturnEmptyString() throws Exception {
		String expectedOutput = "";

		List<String> input = Collections.emptyList();
		String output = Solution.processMessages(input);
		
		assertEquals(expectedOutput, output);
	}
	
}
