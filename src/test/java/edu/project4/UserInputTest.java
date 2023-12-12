package edu.project4;

import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import edu.project4.Render.MultipleThreadRenderer;
import edu.project4.Tranformation.TransformationList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {

    @Test
    void getFlame() {
        FractalImage image = new UserInput().getFlame(
            new MultipleThreadRenderer(),
            List.of(TransformationList.Heart.getTransformation()),
            1200,
            900,
            2.0,
            2.0,
            100000,
            200,
            1
        );

        assertNotNull(image);
        assertTrue(Arrays.stream(image.data()).flatMap(Arrays::stream).filter(Objects::nonNull).
            mapToInt(Pixel::hitCount).sum() > 0);
    }
}
