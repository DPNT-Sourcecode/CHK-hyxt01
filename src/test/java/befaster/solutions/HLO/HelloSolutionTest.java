package befaster.solutions.HLO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class HelloSolutionTest {
    private HelloSolution helloSolution;

    @BeforeEach
    public void setUp() {
        helloSolution = new HelloSolution();
    }

    @Test
    public void hello() {
        assertThat(helloSolution.hello("World"), equalTo("Hello, World!"));
    }
}