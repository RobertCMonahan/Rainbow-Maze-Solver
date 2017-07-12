import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UtilsTest {
    @Test
    public void getGreatestCommonFactor() throws Exception {


                assertEquals(2, Utils.getGreatestCommonFactor(268442, 178296), "GCF of 268442 and 178296 = 2");
                assertEquals(25, Utils.getGreatestCommonFactor(75, 25), "GCF of 75 and 25 = 25");
                assertEquals(5, Utils.getGreatestCommonFactor(65, 105), "GCF of 105 and 65 = 5");
                assertEquals(1, Utils.getGreatestCommonFactor(1, 1), "GCF of 1 and 1 = 1");
                assertEquals(0, Utils.getGreatestCommonFactor(0, 0), "GCF of 0 and 0 = 0");
                assertEquals(1, Utils.getGreatestCommonFactor(1, 1000000000), "GCF of 1 and 1000000000 = 1");
                assertEquals(7, Utils.getGreatestCommonFactor(49, 287), "GCF of 49 and 287 = 7");
                assertEquals(49, Utils.getGreatestCommonFactor(49, 49), "GCF of 49 and 49 = 49");
            }





}