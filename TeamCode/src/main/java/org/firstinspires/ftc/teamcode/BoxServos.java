package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BoxServos {

    private Servo right;
    private Servo left;


    public void configure(Config withConfig, HardwareMap accessor) {
        left  = accessor.servo.get(withConfig.boxLeft());
        right = accessor.servo.get(withConfig.boxRight());
    }

    void Move(Gamepad gamepad) {
        if (gamepad.a) {

            switch ((int) left.getPosition()) {
                case 0:   left.setPosition(1);
                          right.setPosition(0);
                          break;
                case 1: left.setPosition(0);
                        right.setPosition(1);
                        break;

            }
        }
    }

}
