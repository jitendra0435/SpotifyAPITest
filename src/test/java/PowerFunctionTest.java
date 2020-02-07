import org.junit.Assert;
import org.junit.Test;

public class PowerFunctionTest {

    @Test
    public void givenMethodFor_PowerOfTheNumber_ShouldReturnrResult() {

        PowerFunction powerFunction=new PowerFunction();
         int result=powerFunction.getPowerOfNumber(2,5);
        Assert.assertEquals(result,32);

    }

    @Test
    public void givenMethodFor_IfNumberIsNotInteger_ShouldReturnResult() {

        PowerFunction powerFunction=new PowerFunction();
        int result=powerFunction.getPowerOfNumber(-2,-5);
        Assert.assertNotEquals(result,-32);
    }

    @Test
    public void givenMethodFor_IfNumberIsZero_ShouldReturnResult() {

        PowerFunction powerFunction=new PowerFunction();
        int result=powerFunction.getPowerOfNumber(2,0);
        Assert.assertEquals(result,1);
    }

}
