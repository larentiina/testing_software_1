package larentina.task3.science;


import larentina.task3.space.SpaceBody;
import larentina.task3.utils.Time;

import java.util.List;

public class Scientist {

    private static final int INTERVAL = 200;

    public void watch(Telescope telescope, int totalTicks) throws InterruptedException {
        int prevTime = Time.getCurrentTime();
        for (int i = 0; i < totalTicks; i++) {
            while (Time.getCurrentTime() == prevTime && Time.getCurrentTime() < totalTicks) {
                Thread.sleep(INTERVAL);
            }

            List<SpaceBody> bodies = telescope.activate();
            bodies.forEach(Report::createNote);

            prevTime = Time.getCurrentTime();
        }
    }

}
