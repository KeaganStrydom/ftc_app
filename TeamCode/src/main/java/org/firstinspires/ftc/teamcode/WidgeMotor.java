package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WidgeMotor {

    private DcMotor motor;


    public void configure(Config withConfig, HardwareMap accessor) {
        motor  = accessor.dcMotor.get(withConfig.widgeMotor());
    }

    void Move(Gamepad gamepad) {
        final double ACTIVE = 1.0;

        if (gamepad.left_trigger == ACTIVE) {
            Move.up(motor);
        } else if (gamepad.right_trigger == ACTIVE) {
            Move.down(motor);
        } else {
            Move.stop(motor);
        }
    }
}
