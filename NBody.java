package examples;

import examples.In;
import org.junit.Test;

public class NBody {
    static double radius;

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Planet[] pla = NBody.readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1e12, 1e12);
        StdDraw.clear();
        StdDraw.picture(2, 2, "./images/starfield.jpg");
        for (Planet pp : pla) {
            pp.draw();
        }
        StdDraw.show();
        StdDraw.pause(2000);

        double count = 0;
        while (count < T) {
            double[] xForces = new double[pla.length];
            double[] yForces = new double[pla.length];
            for (int i = 0; i < pla.length; i++) {
                xForces[i] = pla[i].CalcNetForceExertedByX(pla);
                yForces[i] = pla[i].CalcNetForceExertedByY(pla);
                pla[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(2, 2, "./images/starfield.jpg");
            for (Planet pp : pla) {
                pp.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            dt++;
            count++;
        }
        //print universe info
        System.out.println(pla.length);
        System.out.println(radius);
       for (Planet pl:pla){
           System.out.print(pl.xxPos + " ");
           System.out.print(pl.yyPos + " ");
           System.out.print(pl.xxVel + " ");
           System.out.print(pl.yyVel + " ");
           System.out.print(pl.mass + "  ");
           System.out.println(pl.imgFileName);
       }
    }

    @Test
    public void testbg() {
        String imageToDraw = "earth.gif";
        StdDraw.setScale(-1000, 1000);
//		157788000 25000 x y
        StdDraw.picture(1, 2, imageToDraw);
        StdDraw.show();
        StdDraw.pause(100000);
    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);

        int num = in.readInt();
        Planet[] rcv = new Planet[num];
        radius = in.readDouble();
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            rcv[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return rcv;
    }
}