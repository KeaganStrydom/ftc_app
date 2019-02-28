package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

public class CollectionServos {

    private CRServo right;
    private CRServo left;

    public void configureWith(HardwareMap accessor) {

        final Config withConfig = new Config();

        this.left  = accessor.crservo.get(withConfig.collectionLeft());
        this.right = accessor.crservo.get(withConfig.collectionRight());
    }


    void moveUsing(Gamepad gamepad) {
        if (gamepad.a) {

            // Feed in balls

            right.setPower(-1.0);
            left.setPower(-1.0);


        } else if (gamepad.b) {

            // Feed out balls

            right.setPower(1.0);
            left.setPower(1.0);

        } else {

            // Stationary

            right.setPower(0.0);
            left.setPower(0.0);
        }
    }


}
