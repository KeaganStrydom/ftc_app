package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "FTC Robot", group = "TeleOp")
public class RobotTeleOp extends OpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private Config config;

    @Override
    public void init() {
        config = new Config();
        initMotors();
    }

    @Override
    public void loop() {
        updateMotorPower();
    }

    @Override
    public void stop() {
        updateMotorPower();
    }


//    public void runOpMode() throws InterruptedException
//    {
//        config = new Config();
//        initMotors();
//        waitForStart();
//
//        while(opModeIsActive()) {
//            updateMotorPower();
//            idle();
//        }
//    }

    private void initMotors()
    {
        motorRight = hardwareMap.dcMotor.get(config.getMotorRight());
        motorLeft = hardwareMap.dcMotor.get(config.getMotorLeft());
//
//        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        inverseMotorDirections();
    }

    private void inverseMotorDirections()
    {
        // Inverse directions of motors.
        motorRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void updateMotorPower()
    {
        motorLeft.setPower(20);
        motorRight.setPower(20);
    }
}

