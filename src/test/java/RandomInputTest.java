import Backend.Cells;
import org.junit.Test;

public class RandomInputTest {

    @Test
    public void random(){
        Cells cells=new Cells(3,4);
        cells.nextState();
    }
}
