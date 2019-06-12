import org.junit.Assert;
import org.junit.Test;

public class TestHelloWorld {

    @Test
    public void hello_world_test(){
        System.out.println("Hello world");
    }

    @Test
    public void test_one(){
        TestMain t=new TestMain();
        Assert.assertEquals(t.testOne(),1);
    }

    @Test
    public void test(){
        TestMain t=new TestMain();
        Assert.assertEquals(t.test(),2);
    }
}
