package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "FTC Robot", group = "RobotStuff")
public class RobotTeleOp extends LinearOpMode {

    private DriveTrain driveTrain;
    private BoxServos boxServos;
    private CollectionServos collectionServos;
    private WidgeMotor widgeMotor;

    public void runOpMode() throws InterruptedException
    {
        boxServos.configureWith(hardwareMap);
        widgeMotor.configureWith(hardwareMap);
        driveTrain.configureWith(hardwareMap);
        collectionServos.configureWith(hardwareMap);

        waitForStart();

        while(opModeIsActive()) {

            driveTrain.moveUsing(gamepad1);

            widgeMotor.moveUsing(gamepad2);

            collectionServos.moveUsing(gamepad2);

            boxServos.updatePositionsUsing(gamepad2);

            idle();
        }
    }

}
