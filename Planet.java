package examples;

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double CalcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        double r = Math.sqrt((dx * dx) + (dy * dy));
        return r;
    }

    public double CalcForceExertedBy(Planet p) {
        double r2 = CalcDistance(p);
        return ((G * mass * p.mass) / (r2 * r2));
    }


    //计算两个星球之间各个方向的作用力
    public double CalcForceExertedByX(Planet p) {
        return ((p.xxPos - xxPos) / CalcDistance(p)) * CalcForceExertedBy(p);
    }

    public double CalcForceExertedByY(Planet p) {
        return ((p.yyPos - yyPos) / CalcDistance(p)) * CalcForceExertedBy(p);
    }

    //计算星球收到其他所有星球之间各个方向的作用力矢量和
    public double CalcNetForceExertedByX(Planet[] p) {
        double sum_force = 0;
        for (Planet pla : p) {
            if (this.equals(pla))
                continue;
            else {
                sum_force += ((pla.xxPos - xxPos) / CalcDistance(pla)) * CalcForceExertedBy(pla);
            }
        }
        return sum_force;
    }

    public double CalcNetForceExertedByY(Planet[] p) {
        double sum_force = 0;
        for (Planet pla : p) {
            if (this.equals(pla))
                continue;
            else {
                sum_force += ((pla.yyPos - yyPos) / CalcDistance(pla)) * CalcForceExertedBy(pla);
            }
        }
        return sum_force;
    }

    public void update(double t, double Fx, double Fy) {
        double ax = Fx / mass;
        double ay = Fy / mass;

        xxVel = xxVel + ax * t;
        yyVel = yyVel + ay * t;

        xxPos = xxPos + t * xxVel;
        yyPos = yyPos + t * yyVel;

    }

    public void draw() {
        // 使用 StdDraw API 上面提到在行星的位置绘制行星的图像
        String filename = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, filename);
//        StdDraw.show();
//        StdDraw.pause(300);
    }
}

// public double CalcForceExertedByX(Planet[] p){
//     	double sum_force = 0;
//     	for(Planet pla : p){
//     		if(this.equals(pla))
//     			continue;
//     		else{
//     			sum_forece += ( (p.xxPos - xxPos) / CalcDistance(p) ) * CalcForceExertedBy(p);
//     		}
//     	}
//     }

//      public double CalcForceExertedByY(Planet[] p){
// 		double sum_force = 0;
//     	for(Planet pla : p){
//     		if(this.equals(pla))
//     			continue;
//     		else{
//     			sum_forece += ( (p.yyPos - yyPos) / CalcDistance(p) ) * CalcForceExertedBy(p);
//     		}
//     	}
//     }

// public double CalcForceExertedByX(Planet p){
// 		return ( (p.xxPos - xxPos) / CalcDistance(p) ) * CalcForceExertedBy(p);
//     }

//    public double CalcForceExertedByY(Planet p){
// 		return ( (p.yyPos - yyPos) / CalcDistance(p) ) * CalcForceExertedBy(p);
//     }