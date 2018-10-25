package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "FTC Robot", group = "RobotStuff")
public class RobotTeleOp extends LinearOpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;
   // private Config config;



    public void runOpMode() throws InterruptedException
    {
        initMotors();
        waitForStart();

        while(opModeIsActive()) {
            updateMotorPower(gamepad1.left_stick_y);
            idle();
        }
    }

    private void initMotors()
    {
        //config = new Config();
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");

        inverseMotorDirections();
    }

    private void inverseMotorDirections()
    {
        // Inverse directions of motors.
        motorRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void updateMotorPower(float power)
    {
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }
}

