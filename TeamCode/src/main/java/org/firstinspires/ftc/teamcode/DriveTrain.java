package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {

    private DcMotor right;
    private DcMotor left;

    public void configureWith(HardwareMap accessor) {

        final Config withConfig = new Config();

        left  = accessor.dcMotor.get(withConfig.drivetrainLeft());
        right = accessor.dcMotor.get(withConfig.drivetrainLeft());

        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void moveUsing(Gamepad gamepad) {
        left.setPower(getPower(Motors.LEFT, gamepad));
        right.setPower(getPower(Motors.RIGHT, gamepad));
    }


    private double getPower(int position, Gamepad gamepad)  {
        final double STATIONARY = 0.0;

        double analogue_y = gamepad.left_stick_y;
        double analogue_x = gamepad.right_stick_x;


        if (notEqual(analogue_y, STATIONARY)) {

            double power = (analogue_y - (Math.abs(analogue_y) / analogue_y) * ((analogue_x + position) * (analogue_x * 0.5))) / 2.5;

            return power;

        } else {
            return STATIONARY;
        }

    }

    private Boolean notEqual(double item, double equatable ) {
        return (item != equatable);
    }

}
