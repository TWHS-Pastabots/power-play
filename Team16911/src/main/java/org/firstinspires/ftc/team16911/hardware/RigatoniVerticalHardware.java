package org.firstinspires.ftc.team16911.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.internal.system.Assert;


//HARDWARE FOR HORIZONTAL CLAW
public class RigatoniVerticalHardware
{
    // Primary Motors
    public DcMotorEx leftFront = null;
    public DcMotorEx leftRear = null;
    public DcMotorEx rightFront = null;
    public DcMotorEx rightRear = null;

    public DcMotorEx[] motors;

    // Supplementary Motors
    public DcMotorEx liftArm = null;

    // CLaw Servos
    public Servo grabServo = null;
    public Servo rotServo = null;


    public void init(HardwareMap hardwareMap)
    {
        Assert.assertNotNull(hardwareMap);
        initializePrimaryMotors(hardwareMap);
        initializeClawServos(hardwareMap);


    }
    public void initializePrimaryMotors(HardwareMap hardwareMap)
    {
        motors = new DcMotorEx[]{leftFront, leftRear, rightFront, rightRear, liftArm};


        // Primary Motors
        leftFront = hardwareMap.get(DcMotorEx.class, RigatoniIds.LEFT_FRONT_MOTOR);
        leftRear = hardwareMap.get(DcMotorEx.class, RigatoniIds.LEFT_REAR_MOTOR);
        rightFront = hardwareMap.get(DcMotorEx.class, RigatoniIds.RIGHT_FRONT_MOTOR);
        rightRear = hardwareMap.get(DcMotorEx.class, RigatoniIds.RIGHT_REAR_MOTOR);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);


        // Supplementary Motors
        liftArm = hardwareMap.get(DcMotorEx.class, RigatoniIds.LIFT_ARM_MOTOR);





        // Set Zero Power Behavior and Initialize Motors
        for (DcMotorEx motor : motors)
        {
            motor.setPower(0);
            motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
    public void initializeClawServos(HardwareMap hardwareMap)
    {
        // Claw Servos
        grabServo = hardwareMap.get(Servo.class, RigatoniIds.GRAB_SERVO);
        rotServo = hardwareMap.get(Servo.class, RigatoniIds.ROT_SERVO);

        grabServo.setDirection(Servo.Direction.FORWARD);
        grabServo.setPosition(.5);
        rotServo.setDirection(Servo.Direction.FORWARD);
        rotServo.setPosition(0);

    }
}
