package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "FTC Robot", group = "RobotStuff")
public class RobotTeleOp extends LinearOpMode {

    private Config config;

    private DcMotor driveLeft;
    private DcMotor driveRight;

    private DcMotor sliderTop;
    private DcMotor sliderBottom;

    private CRServo collectionLeft;
    private CRServo collectionRight;



    public void runOpMode() throws InterruptedException
    {
        config = new Config();

        initDriveMotors();
        initSlideMotors();

        inverseMotorDirections();

        initCollectionServos();

        waitForStart();

        while(opModeIsActive()) {

           // updateDrivePower(getDrivePower(Motors.LEFT), getDrivePower(Motors.RIGHT));

            updateSliderPower();
            updateCollectionServoPowers();
            idle();
        }
    }


    private void initSlideMotors() {
        sliderTop = hardwareMap.dcMotor.get(config.getMotorTop());
        sliderBottom = hardwareMap.dcMotor.get(config.getMotorBottom());
    }



    private void initDriveMotors() {
        // Set the DCMotor object to the corresponding motor from the pre-defined config
        driveRight = hardwareMap.dcMotor.get(config.getDriveRight());
        driveLeft  = hardwareMap.dcMotor.get(config.getDriveLeft());
        driveRight.setPower(0.0);
        driveLeft.setPower(0.0);
    }

    private void initCollectionServos() {
        // Set the DCMotor object to the corresponding motor from the pre-defined config
        collectionLeft = hardwareMap.crservo.get(config.getCollectionLeft());
        collectionRight = hardwareMap.crservo.get(config.getCollectionRight());

        collectionRight.setDirection(CRServo.Direction.FORWARD);
        collectionRight.setDirection(CRServo.Direction.REVERSE);
    }


    private void inverseMotorDirections() {
        // Inverse directions of drive motors as they face opposite directions.
        driveLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        driveRight.setDirection(DcMotorSimple.Direction.REVERSE);


        sliderTop.setDirection(DcMotorSimple.Direction.FORWARD);
        sliderBottom.setDirection(DcMotorSimple.Direction.FORWARD);
    }


    private void updateCollectionServoPowers() {



        if (gamepad1.a) {
            collectionRight.setPower(1);
            collectionLeft.setPower(1);
        } else {
            collectionRight.setPower(0);
            collectionLeft.setPower(0);
        }

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

    private void updateSliderPower() {

        final double ACTIVE = 1.0;
        final double STOP = 0.0;
        final double UP = 1.0;

        sliderTop.setPower(0.0);

        if (gamepad1.left_trigger == ACTIVE) {
            sliderTop.setPower(1.0);
        } else if (gamepad1.right_trigger == ACTIVE) {
            sliderTop.setPower(-1.0);
        }
    }

    private void updateDrivePower(double powerLeft, double powerRight) {
       // driveLeft.setPower(powerRight);


        //driveRight.setPower(powerLeft);



        telemetry.clearAll();
        telemetry.addLine(String.format("%.2f", gamepad1.left_trigger)  + " : " +  String.format("%.2f", gamepad1.right_trigger));
        telemetry.update();

    }
}
