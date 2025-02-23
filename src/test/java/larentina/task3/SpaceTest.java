package larentina.task3;

import larentina.task3.science.Report;
import larentina.task3.science.Scientist;
import larentina.task3.science.Telescope;
import larentina.task3.space.*;
import larentina.task3.space.state.PlanetState;
import larentina.task3.space.state.StarState;
import larentina.task3.utils.Time;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpaceTest {

    @Test
//    @Order(1)
    public void testShadowSun() throws InterruptedException {
        Space space = new Space(20, 20);
        space.init();
        Time.reset();

        space.start(5);
        Assertions.assertEquals(space.findStar().getState(), StarState.SHADOWING);
    }

    @Test
//    @Order(2)
    public void testLightSun() throws InterruptedException {
        Space space = new Space(20, 20);
        space.init();
        Time.reset();
        space.start(10);
        Assertions.assertEquals(space.findStar().getState(), StarState.LIGHTING);
    }

    @Test
//    @Order(3)
    public void testStartingState() throws InterruptedException {
        Space space = new Space(20, 20);
        space.init();
        Time.reset();

        space.start(0);
        Assertions.assertAll(() -> space.getBodies().forEach(b -> {
            if (b instanceof Planet) {
                Assertions.assertEquals(PlanetState.STARTING, ((Planet) b).getState());
            } else if (b instanceof Star) {
                Assertions.assertEquals(StarState.STARTING, ((Star) b).getState());
            }
        }));
    }

    @Test
//    @Order(4)
    public void testPlanetShadow() throws InterruptedException {
        Time.reset();
        Space space = new Space(20, 20);
        space.init();
        Time.reset();
        space.start(5);
        Assertions.assertTrue(space.getBodies().stream().filter(b -> b instanceof Planet).anyMatch(b -> ((Planet) b).getState().equals(PlanetState.SHADOW)));
    }

    @Test
//    @Order(5)
    public void testPlanetMoving() throws InterruptedException {
        Time.reset();
        Space space = new Space(20, 20);
        space.init();
        Time.reset();
        space.start(9);
        Assertions.assertTrue(space.getBodies().stream().filter(b -> b instanceof Planet).anyMatch(b -> ((Planet) b).getState().equals(PlanetState.MOVING)));

    }

    @Test()
    @RepeatedTest(1)
//    @Order(6)
    public void nullSpaceBody() {
        Time.reset();
        Space space = new Space(20, 20);
        Assertions.assertNull(space.findStar());
        space.setBodies(new ArrayList<SpaceBody>(){{add(new Planet(new Coordinates(1.0d,2.0d),1.0f,1.0f));}});
        Assertions.assertNull(space.findStar());
    }

    @Test
//    @Order(7)
    public void planetOutOfBounds() throws InterruptedException {
        Time.reset();
        Space space = new Space(5, 5);
        space.setBodies(null);
        Planet planet = new Planet(new Coordinates(1.0,2.0),1.0f,2.0f);
        space.setBodies(new ArrayList<SpaceBody>(){{add(planet);}});
        space.start(3);
        Assertions.assertEquals(planet.getState(),PlanetState.OUT_OF_BOUNDS);

        Time.reset();
        planet.setCoordinates(new Coordinates(-10.0,4.0));
        space.setBodies(new ArrayList<SpaceBody>(){{add(planet);}});
        space.start(1);
        Assertions.assertEquals(planet.getState(),PlanetState.OUT_OF_BOUNDS);

    }

    @Test
//    @Order(8)
    public void testScienceWatch() throws InterruptedException {
        Space space = new Space(20, 20);
        space.init();
        Time.reset();

        Thread thread = new Thread(() -> {
            try {
                space.start(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Scientist scientist = new Scientist();
        Telescope telescope = new Telescope(space);
        scientist.watch(telescope, 5);
        thread.join();
        Assertions.assertEquals(15, Report.getNotes().size());
    }

    @Test
    public void testReport(){
        Report.makeEmptyNotes();
        Report.createNote(null);
        Assertions.assertEquals(0,Report.getNotes().size());
    }






}
