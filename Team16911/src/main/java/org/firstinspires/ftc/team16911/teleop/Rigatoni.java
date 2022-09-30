package org.firstinspires.ftc.team16911.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team16911.hardware.RigatoniHardware;


public class Rigatoni extends OpMode {
    RigatoniHardware hardware;
    final double FAST_SPEED = .8;
    final double SLOW_SPEED = .5;
    double slowConstant = FAST_SPEED;

    ElapsedTime buttonTime = null;
    @Override
    public void init() {
        hardware = new RigatoniHardware();
        hardware.init(hardwareMap);
        buttonTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    }

    @Override
    public void loop() {
        drive();
        moveArm();
        rotateClaw();
    }
    public void drive() {
        // Mecanum drivecode
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        double leftFrontPower = y + x + rx;
        double leftRearPower = y - x + rx;
        double rightFrontPower = y - x - rx;
        double rightRearPower = y + x - rx;


        if (Math.abs(leftFrontPower) > 1 || Math.abs(leftRearPower) > 1 ||
                Math.abs(rightFrontPower) > 1 || Math.abs(rightRearPower) > 1 )
        {
            // Find the largest power
            double max;
            max = Math.max(Math.abs(leftFrontPower), Math.abs(leftRearPower));
            max = Math.max(Math.abs(rightFrontPower), max);
            max = Math.max(Math.abs(rightRearPower), max);

            // Divide everything by max (it's positive so we don't need to worry
            // about signs)
            leftFrontPower /= max;
            leftRearPower /= max;
            rightFrontPower /= max;
            rightRearPower /= max;
        }

        if (gamepad1.dpad_up || gamepad1.dpad_right)
        {
            leftFrontPower = -1;
            rightRearPower = -1;
            rightFrontPower = 1;
            leftRearPower = 1;
        }
        else if (gamepad1.dpad_down || gamepad1.dpad_left)
        {
            leftFrontPower = 1;
            rightRearPower = 1;
            rightFrontPower = -1;
            leftRearPower = -1;
        }

        if (gamepad1.square && slowConstant == FAST_SPEED && buttonTime.time() >= 500)
        {
            slowConstant = SLOW_SPEED;
            buttonTime.reset();
        }
        else if (gamepad1.square && slowConstant == SLOW_SPEED && buttonTime.time() >= 500)
        {
            slowConstant = FAST_SPEED;
            buttonTime.reset();
        }

        hardware.leftFront.setPower(leftFrontPower * slowConstant);
        hardware.leftRear.setPower(leftRearPower * slowConstant);
        hardware.rightFront.setPower(rightFrontPower * slowConstant);
        hardware.rightRear.setPower(rightRearPower * slowConstant);


    }
    public void moveArm()
    {
        hardware.liftArm.setPower((gamepad2.right_trigger - gamepad2.left_trigger)*.5);
    }
    public void rotateClaw()
    {
        hardware.
    }
}
