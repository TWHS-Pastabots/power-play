package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.SpaghettiHardware;


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
    final double openClaw = 0;
    final double closeClaw = 0.5;

    public void init()
    {
        // Initialize Hardware
        hardware = new SpaghettiHardware();
        hardware.init(hardwareMap);
        buttonTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

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
    public void moveLift()
    {
        //GAMEPAD 2
    }
    public void clawGrasp()
    {
        //when claw opens and closes, reset rotating cone servos
        //GAMEPAD 2

        if (gamepad2.right_bumper)
        {
            if (hardware.clawServo.getPosition() == openClaw) {

                hardware.clawServo.setPosition(closeClaw);
            }
            else
            {
                hardware.clawServo.setPosition(openClaw);
            }
        }
    }
    public void rotateCone()
    {
        //triangle and x
        //GAMEPAD 2
    }

}
