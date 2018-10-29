package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "FTC Robot", group = "RobotStuff")
public class RobotTeleOp extends LinearOpMode {

    private Config config;

    private DcMotor driveLeft;
    private DcMotor driveRight;

    private CRServo deliveryServoLeft;
    private CRServo deliveryServoRight;

    private CRServo conveyorLeft;
    private CRServo conveyorRight;

    public void runOpMode() throws InterruptedException
    {
        config = new Config();

        initDriveMotors();
        inverseMotorDirections();
        
        initDeliveryServos();
        initConveyorServos();
        inverseServoDirections();

        waitForStart();

        while(opModeIsActive()) {

            updateDrivePower(getDrivePower(Motors.LEFT), getDrivePower(Motors.RIGHT));

            updateDeliveryServoPower(gamepad2.left_stick_y, gamepad2.left_stick_y);

            idle();
        }
    }

    private void initDriveMotors() {
        driveRight = hardwareMap.dcMotor.get(config.getMotorLeft());
        driveLeft  = hardwareMap.dcMotor.get(config.getMotorRight());

        inverseMotorDirections();
    }

    private void initDeliveryServos() {
        deliveryServoLeft  = hardwareMap.crservo.get(config.getDeliveryServoLeft());
        deliveryServoRight = hardwareMap.crservo.get(config.getDeliveryServoRight());

        inverseServoDirections();
    }

    private void initConveyorServos() {
        conveyorLeft  = hardwareMap.crservo.get(config.getConveyorLeft());
        conveyorRight = hardwareMap.crservo.get(config.getConveyorRight());

        inverseServoDirections();
    }


    private void inverseServoDirections() {
        // Inverse directions of servos.
        deliveryServoRight.setDirection(CRServo.Direction.FORWARD);
        deliveryServoLeft.setDirection(CRServo.Direction.REVERSE);

        conveyorRight.setDirection(CRServo.Direction.REVERSE);
        conveyorLeft.setDirection(CRServo.Direction.FORWARD);
    }

    private void inverseMotorDirections() {
        // Inverse directions of motors.
        driveLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        driveRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void updateDeliveryServoPower(double servoPowerLeft, double servoPowerRight) {
        deliveryServoLeft.setPower(servoPowerLeft);
        deliveryServoRight.setPower(servoPowerRight);
    }

    private double getDrivePower(int MotorPosition) {
        return  gamepad1.left_stick_y + ((gamepad1.right_stick_x + MotorPosition) * (gamepad1.right_stick_x * 0.5));
    }

    private void updateDrivePower(double powerLeft, double powerRight) {
        driveLeft.setPower(powerRight);
        driveRight.setPower(powerLeft);
    }
}

