package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "FTC Robot", group = "RobotStuff")
public class RobotTeleOp extends LinearOpMode {



    private DcMotor driveLeft;
    private DcMotor driveRight;

    private DcMotor sliderTop;

    private CRServo collectionLeft;
    private CRServo collectionRight;

    private BoxServos boxServos;

    private CollectionServos collectionServos;

    private WidgeMotor widgeMotor;


    public void runOpMode() throws InterruptedException
    {

        initDriveMotors();
        initSlideMotors();

        boxServos.configure(new Config(), hardwareMap);
        collectionServos.configure(new Config(), hardwareMap);
        widgeMotor.configure(new Config(), hardwareMap);

        inverseMotorDirections();

        waitForStart();

        while(opModeIsActive()) {

            updateDrivePower(getDrivePower(Motors.LEFT), getDrivePower(Motors.RIGHT));

            updateSliderPower();
            updateCollectionServoPowers();



            boxServos.Move(gamepad2);
            widgeMotor.Move(gamepad2);
            collectionServos.Move(gamepad2);

            idle();
        }
    }




    private void initSlideMotors() {
        sliderTop = hardwareMap.dcMotor.get(config.getMotorTop());
    }



    private void initDriveMotors() {
        // Set the DCMotor object to the corresponding motor from the pre-defined config
        driveRight = hardwareMap.dcMotor.get(config.getDriveRight());
        driveLeft  = hardwareMap.dcMotor.get(config.getDriveLeft());
        driveRight.setPower(0.0);
        driveLeft.setPower(0.0);
    }


    private void inverseMotorDirections() {
        // Inverse directions of drive motors as they face opposite directions.
        driveLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        driveRight.setDirection(DcMotorSimple.Direction.REVERSE);


        sliderTop.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    private double getDrivePower(int MotorPosition) {

        //Gets the power for the specified motor to control movement.

        //  Extract the inputs from the game-pad controller 1
        //  game-pad controller 1 controls the robots movement.


        double analogue_y = gamepad1.left_stick_y;
        double analogue_x = gamepad1.right_stick_x;

        if (analogue_y != 0.0) {

            double power = (analogue_y - (Math.abs(analogue_y) / analogue_y) * ((analogue_x + MotorPosition) * (analogue_x * 0.5))) / 2.5;

            return power;

        } else {
            return 0.0;
        }

        //  Example:
        //  Driving Backward, to the left

        //  Right Motor Power = -1 - (1 / -1) * ((-1) + 1) * (-1 * 0.2)
        //                    = -1 - (-1) * (0) * (-0.2)
        //                    = -1

        //  Left Motor Power  = -1 - (1 / -1) * ((-1) - 1) * (-1 * 0.2)
        //                    = -1 - (-1) * (-2) * (-1 * 0.2)
        //                    = -1 - (2 * -0.2)
        //                    = -0.6
    }




    private void updateDrivePower(double powerLeft, double powerRight) {
        driveLeft.setPower(powerRight);

        driveRight.setPower(powerLeft);
    }
}
