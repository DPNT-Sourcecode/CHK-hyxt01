package befaster.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CheckoutSolutionTest {
    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp() {
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void testEmpty() {
        assertThat(checkoutSolution.checkout(""), equalTo(0));
    }

    @Test
    public void testInvalid() {
        assertThat(checkoutSolution.checkout("123"), equalTo(-1));
        assertThat(checkoutSolution.checkout("1234"), equalTo(-1));
        assertThat(checkoutSolution.checkout("aa"), equalTo(-1));
    }

    @Test
    public void testBasicPrices() {
        assertThat(checkoutSolution.checkout("A"), equalTo(50));
        assertThat(checkoutSolution.checkout("B"), equalTo(30));
        assertThat(checkoutSolution.checkout("C"), equalTo(20));
        assertThat(checkoutSolution.checkout("D"), equalTo(15));
    }

    @Test
    public void testDiscounts() {
        assertThat(checkoutSolution.checkout("AB"), equalTo(80));
        assertThat(checkoutSolution.checkout("AAA"), equalTo(130));
        assertThat(checkoutSolution.checkout("AA"), equalTo(100));
        assertThat(checkoutSolution.checkout("AAAA"), equalTo(180));
        assertThat(checkoutSolution.checkout("BBBB"), equalTo(90));
        assertThat(checkoutSolution.checkout("BBB"), equalTo(75));
    }

    @Test
    public void testFreeBWithE() {
        assertThat(checkoutSolution.checkout("EEB"), equalTo(80));
        assertThat(checkoutSolution.checkout("EEEB"), equalTo(120));
        assertThat(checkoutSolution.checkout("EEEBB"), equalTo(150));
        assertThat(checkoutSolution.checkout("EEBB"), equalTo(110));

        assertThat(checkoutSolution.checkout("AAAAA"), equalTo(200));
        assertThat(checkoutSolution.checkout("AAAAAA"), equalTo(250));
        assertThat(checkoutSolution.checkout("AAAAAAA"), equalTo(300));

        assertThat(checkoutSolution.checkout("AAA"), equalTo(130));
        assertThat(checkoutSolution.checkout("AAAA"), equalTo(180));

        assertThat(checkoutSolution.checkout("AAAAAAAA"), equalTo(330));
        assertThat(checkoutSolution.checkout("AAAAAAAAA"), equalTo(380));
    }
}