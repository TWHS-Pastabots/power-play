package org.firstinspires.ftc.team16910.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team16910.Hardware.SpaghettiHardware;


@TeleOp(name = "Spaghetti")
public class Spaghetti extends OpMode
{
    // Initialization
    SpaghettiHardware hardware;
    final double FAST_SPEED = .8;
    final double SLOW_SPEED = .5;
    double slowConstant = FAST_SPEED;
    ElapsedTime armTime = null;
    ElapsedTime buttonTime = null;
    final double halfClaw = 0.5;
    final double closeClaw = 0;
    // Rotating wheels on claw
    final double wheelForward = 0;
    final double wheelBackward = 1;

    public void init()
    {
        // Initialize Hardware

        hardware = new SpaghettiHardware();
        hardware.init(hardwareMap);
        buttonTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        armTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public void start()
    {
        telemetry.addData("Status", "Started");
        telemetry.update();
    }
    public void loop()
    {
        drive();
        moveLift();
        clawGrasp();
        rotateCone();
    }

    private void drive()
    {
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
    private void moveLift()
    {
        // Operator

            hardware.liftMotor.setPower(gamepad2.left_stick_y);
            hardware.liftMotor2.setPower(gamepad2.left_stick_y);
    }

    private void clawGrasp()
    {
        if (gamepad2.right_bumper && buttonTime.time() >= 500)
        {
            telemetry.addData("Servo position:", hardware.leftClaw.getPosition());
            telemetry.update();
            if (hardware.leftClaw.getPosition() == halfClaw)
            {
                hardware.leftClaw.setPosition(closeClaw);
                hardware.rightClaw.setPosition(closeClaw);
                buttonTime.reset();

               telemetry.addData("Servo position:", hardware.leftClaw.getPosition());
               telemetry.update();
            }
            else if (hardware.leftClaw.getPosition() == closeClaw)
            {
                hardware.leftClaw.setPosition(halfClaw);
                hardware.rightClaw.setPosition(halfClaw);
                buttonTime.reset();

                telemetry.addData("Servo position:", hardware.leftClaw.getPosition());
                telemetry.update();
            }
            // upon beginning the game, claw only switches between close and half

            if (hardware.rightClaw.getPosition() == 1)
            {
                hardware.leftClaw.setPosition(halfClaw);
                hardware.rightClaw.setPosition(halfClaw);
                buttonTime.reset();
            }

        }
        if (gamepad2.left_bumper && buttonTime.time() >= 500)
        {
            hardware.leftClaw.setPosition(1);
            hardware.rightClaw.setPosition(1);
            buttonTime.reset();

            telemetry.addData("Servo position:", hardware.leftClaw.getPosition());
            telemetry.update();

        }
    }

    private void rotateCone()
    {
        // rotate cone 90 degrees forwards or backwards
        // operator
        if (gamepad2.triangle)
        {
            hardware.wheelServoL.setPosition(wheelForward);
            hardware.wheelServoR.setPosition(wheelForward);

            telemetry.addData("Wheel Position: ", "Forward");
            telemetry.update();

        }
        if (gamepad2.cross)
        {
            hardware.wheelServoL.setPosition(wheelBackward);
            hardware.wheelServoR.setPosition(wheelBackward);

            telemetry.addData("Wheel Position: ", "Backward");
            telemetry.update();

        }
        if (gamepad2.square)// reset to normal position
        {
            hardware.wheelServoL.setPosition(0.5);
            hardware.wheelServoR.setPosition(0.5);

            telemetry.addData("Wheel Position: ", "Reset");
            telemetry.update();
        }

    }

}
