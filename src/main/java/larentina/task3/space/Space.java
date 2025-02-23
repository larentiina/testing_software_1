package larentina.task3.space;

import larentina.task3.space.state.Color;
import larentina.task3.utils.Time;

import java.util.ArrayList;
import java.util.List;



public class Space {
    private double width;
    private double height;
   private List<SpaceBody> bodies;

   public Space(double width, double height) {
       this.width = width;
       this.height = height;
   }

   public void init(){
       bodies = new ArrayList<>();
       bodies.add(new Star(new Coordinates(5.0, (5.0)),5.0f, Color.RED));
       bodies.add(new Planet(new Coordinates(1.0,3.0),4.0f,1.0f));
   }

   public void start(int time) throws InterruptedException {
        while (time>Time.getCurrentTime()){
            Time.tick();
            for (SpaceBody b : bodies){
                if(b instanceof Planet){
                    Planet p = (Planet)b;
                    p.move();
                    Star star = findStar();
                    if(star != null && p.checkCollisionWithStar(star)){
                        p.castShadow(star);
                    }else if (star != null){
                        star.light(Color.RED);
                    }

                    p.checkBoundsAndWrap(width);
                }
            }
            Thread.sleep(1000);
        }
   }

   public Star findStar(){
       if(bodies!=null) {
           for (SpaceBody b : bodies) {
               if (b instanceof Star) {
                   return (Star) b;
               }
           }
       }
       return null;
   }

    public List<SpaceBody> getBodies() {
        return bodies;
    }

    public void setBodies(List<SpaceBody> bodies) {
        this.bodies = bodies;
    }
}
