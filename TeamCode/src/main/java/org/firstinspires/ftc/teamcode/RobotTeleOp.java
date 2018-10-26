package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "FTC Robot", group = "RobotStuff")
public class RobotTeleOp extends LinearOpMode {

    private DcMotor driveMotorLeft;
    private DcMotor driveMotorRight;
   // private Config config;



    public void runOpMode() throws InterruptedException
    {
        initDriveMotors();
        waitForStart();

        while(opModeIsActive()) {

            updateMotorPower(gamepad1.left_stick_y + ((gamepad1.right_stick_x + 1) * (gamepad1.right_stick_x * 5)), gamepad1.left_stick_y + ((gamepad1.right_stick_x - 1) * (gamepad1.right_stick_x * 5)));


            idle();
        }
    }

    private void initDriveMotors()
    {
        //config = new Config();
        driveMotorRight = hardwareMap.dcMotor.get("motorRight");
        driveMotorLeft = hardwareMap.dcMotor.get("motorLeft");

        inverseMotorDirections();
    }

    private void inverseMotorDirections()
    {
        // Inverse directions of motors.
        driveMotorLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        driveMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void updateMotorPower(float powerLeft, float powerRight)
    {
        driveMotorLeft.setPower(powerRight);
        driveMotorRight.setPower(powerLeft);
    }
}

