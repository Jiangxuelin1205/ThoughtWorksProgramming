import Backend.Cells;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class FileInputTest {

    @Test
    public void file_test() throws IOException {
        Cells cells=new Cells("./src/main/java/cells.txt");
        cells.nextState();
        Assert.assertEquals(cells.cellCurrentState(1,1),1);
    }

}
