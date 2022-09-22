package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.internal.system.Assert;


public class SpaghettiHardware
{
    public DcMotorEx leftFront;
    public DcMotorEx rightFront;
    public DcMotorEx leftRear;
    public DcMotorEx rightRear;
    public DcMotorEx liftMotor;
    public Servo clawServo;
    public Servo wheelServoL;
    public Servo wheelServoR;

    public DcMotorEx[] motors;
    public Servo[] servos;

    public void init(HardwareMap hardwareMap)
    {
        Assert.assertNotNull(hardwareMap);
        initializeMotors(hardwareMap);
    }

    public void initializeMotors(HardwareMap hardwareMap)
    {
        //drive motors
        leftFront = hardwareMap.get(DcMotorEx.class, SpaghettiID.LEFT_FRONT_MOTOR);
        rightFront = hardwareMap.get(DcMotorEx.class, SpaghettiID.RIGHT_FRONT_MOTOR);
        leftRear = hardwareMap.get(DcMotorEx.class, SpaghettiID.LEFT_REAR_MOTOR);
        rightRear = hardwareMap.get(DcMotorEx.class, SpaghettiID.RIGHT_REAR_MOTOR);

        motors = new DcMotorEx[]{leftFront, leftRear, rightFront, rightRear,liftMotor};

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);

        liftMotor = hardwareMap.get(DcMotorEx.class, SpaghettiID.LIFT_MOTOR);


        for (DcMotorEx motor : motors) {
            motor.setPower(0.0);
            motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
    public void initializeClawServos(HardwareMap hardwareMap)
    {
        servos = new Servo[]{clawServo,wheelServoL,wheelServoR};

        clawServo = hardwareMap.get(Servo.class, SpaghettiID.CLAW_SERVO);
        wheelServoL = hardwareMap.get(Servo.class, SpaghettiID.WHEEL_SERVOL);
        wheelServoR = hardwareMap.get(Servo.class, SpaghettiID.WHEEL_SERVOR);

        for(Servo servo : servos)
        {
            servo.setDirection(Servo.Direction.FORWARD);
            servo.setPosition(0);
        }
    }
}
