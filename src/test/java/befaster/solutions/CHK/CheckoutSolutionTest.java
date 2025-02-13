package befaster.solutions.CHK;

import befaster.solutions.HLO.HelloSolution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {
    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp() {
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void testInvalid() {
        assertThat(checkoutSolution.checkout("123"), equalTo(-1));
        assertThat(checkoutSolution.checkout("1234"), equalTo(-1));
        assertThat(checkoutSolution.checkout("aa"), equalTo(-1));
    }

    @Test
    public void testSingleItems() {
        assertThat(checkoutSolution.checkout("A"), equalTo(50));
        assertThat(checkoutSolution.checkout("B"), equalTo(30));
        assertThat(checkoutSolution.checkout("C"), equalTo(20));
        assertThat(checkoutSolution.checkout("D"), equalTo(15));
    }

    @Test
    public void testMultipleItems() {
        assertThat(checkoutSolution.checkout("ABCD"), equalTo(115));
        assertThat(checkoutSolution.checkout("AAABBB"), equalTo(160));
        assertThat(checkoutSolution.checkout("AAABBBD"), equalTo(175));
    }

    @Test
    public void testDiscounts() {
        assertThat(checkoutSolution.checkout("AAA"), equalTo(130));
        assertThat(checkoutSolution.checkout("AAAA"), equalTo(180));
        assertThat(checkoutSolution.checkout("AAAAA"), equalTo(230));
        assertThat(checkoutSolution.checkout("AAAAAA"), equalTo(260));

        assertThat(checkoutSolution.checkout("BB"), equalTo(45));
        assertThat(checkoutSolution.checkout("BBB"), equalTo(75));
        assertThat(checkoutSolution.checkout("BBBB"), equalTo(90));
    }
}