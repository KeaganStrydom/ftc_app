package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Move {

    public static void up(DcMotor dcMotor){
        dcMotor.setPower(1.0);
    }

    public static void down(DcMotor dcMotor){
        dcMotor.setPower(-1.0);
    }

    public static void stop(DcMotor dcMotor){
        dcMotor.setPower(0.0);
    }

}




