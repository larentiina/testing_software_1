package larentina.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.Stream;

public class FuncTest {


    static Stream<Arguments> provideAngles(){
        return Stream.of(
                Arguments.of(0,1.0),
                Arguments.of(Math.PI / 2, 0.0),
                Arguments.of(Math.PI, -1.0),
                Arguments.of(-Math.PI/2,0.0),
                Arguments.of( Math.PI / 4, Math.sqrt(2)/2 ),
                Arguments.of(  - Math.PI / 4, Math.sqrt(2)/2 ),
                Arguments.of(  3*Math.PI / 4, -Math.sqrt(2)/2 ),
                Arguments.of(  -3*Math.PI / 4, -Math.sqrt(2)/2 )
                );
    }
    static Stream<Double> provideRandomValues() {
        Random random = new Random();
        return Stream.generate(() -> random.nextDouble() * (10 - 0.1) + 0.1)
                .limit(100);
    }

    @ParameterizedTest
    @MethodSource("provideAngles")
    void testCosDefaultAngle(double x, double expected) {
        double actual = Cos.cosTaylor(x, 10);
        Assertions.assertEquals(expected, actual, 1e-6);
    }


    @ParameterizedTest
    @MethodSource("provideRandomValues")
    void testCosRandom(double x) {
        double expected = Math.cos(x);
        double actual = Cos.cosTaylor(x, 20);
        Assertions.assertEquals(expected, actual, 1e-4);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0,2.0,3.0})
    void testCosNegativeTerms(double x) {
        double expected = 0;
        double actual = Cos.cosTaylor(x, -1);
        Assertions.assertEquals(expected, actual, 1e-6);
    }
}
