package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class SwerveModule {
    // 宣告馬達
    private final TalonFX driveMotor;
    private final CANSparkMax turningMotor;

    // 宣告Encoder
    //private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turningEncoder;

    // 宣告PID控制器
    private final PIDController turningPidController;

    // 宣告絕對編碼器
    //private final AnalogInput absoluteEncoder;
    private CANcoder absoluteEncoder;


    // 宣告一些變數 $
    private final boolean absoluteEncoderReversed;
    private final double absoluteEncoderOffsetRad;


    /**
     * @param driveMotorId
     * @param turningMotorId
     * @param driveMotorReversed
     * @param turningMotorReversed
     * @param absoluteEncoderId
     * @param absoluteEncoderOffset
     * @param absoluteEncoderReversed
     */

    public SwerveModule(int driveMotorId, int turningMotorId, boolean driveMotorReversed, boolean turningMotorReversed, int absoluteEncoderId, double absoluteEncoderOffset, boolean absoluteEncoderReversed){
        // $
        this.absoluteEncoderReversed = absoluteEncoderReversed;
        this.absoluteEncoderOffsetRad = absoluteEncoderOffset;

        driveMotor = new TalonFX(driveMotorId);
        turningMotor = new CANSparkMax(turningMotorId, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);

        // $
        driveMotor.setInverted(driveMotorReversed);
        turningMotor.setInverted(turningMotorReversed);

        //driveMotor.getPosition();
        //driveMotor.getVelocity();
        turningEncoder = turningMotor.getEncoder();
        //System.out.println("turningEncoder"+turningEncoder);

        turningPidController = new PIDController(Constants.ModuleConstants.kPTurning, 0, 0);
        turningPidController.enableContinuousInput(-Math.PI, Math.PI);

        //absoluteEncoder = new AnalogInput(absoluteEncoderId);
        /* Angle Encoder Config */
        absoluteEncoder = new CANcoder(absoluteEncoderId);
        //absoluteEncoder.getConfigurator().apply(Robot.ctreConfigs.swerveCANcoderConfig);

        //driveEncoder.setPositionConversionFactor(Constants.ModuleConstants.kDriveEncoderRot2Meter);
        //driveEncoder.setVelocityConversionFactor(Constants.ModuleConstants.kDriveEncoderRot2MeterPerSec);
        turningEncoder.setPositionConversionFactor((Constants.ModuleConstants.kTurningEncoderRot2Rad));
        turningEncoder.setVelocityConversionFactor(Constants.ModuleConstants.kTurningEncoderRot2RadPerSec);

        
        turningMotor.burnFlash();
        resetEncoders();
    }

    public double getDrivePosition(){
        return driveMotor.getPosition().getValueAsDouble()*Constants.ModuleConstants.kDriveEncoderRot2Meter;
        //return driveEncoder.getPosition();
    }

    public double getTurningPosition(){
        return turningEncoder.getPosition() / Math.PI;
    }

    public double getDriveVelocity(){
        return driveMotor.getVelocity().getValueAsDouble()*Constants.ModuleConstants.kDriveEncoderRot2MeterPerSec;
        //return driveEncoder.getVelocity();
    }

    public double getTurningVelocity(){
        return turningEncoder.getVelocity();
    }

    public double getAbsoluteEncoderRad(){
        double angle = absoluteEncoder.getAbsolutePosition().getValueAsDouble();
        angle *= 2 * Math.PI;//角度換成弧度
        angle -= absoluteEncoderOffsetRad;
        return angle * (absoluteEncoderReversed ? -1.0 : 1.0);
    }

    public void resetEncoders(){
        //driveEncoder.setPosition(0);
        turningEncoder.setPosition(getAbsoluteEncoderRad());
    }

    public SwerveModuleState getState(){
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }

    public void setDesiredState(SwerveModuleState state){
        if(Math.abs(state.speedMetersPerSecond) < 0.001){
            stop();
            return;
        }
        state = SwerveModuleState.optimize(state, getState().angle);
        driveMotor.set(state.speedMetersPerSecond / Constants.DriveConstants.kPhysicalMaxSpeedMeterPerSecond);
        turningMotor.set(turningPidController.calculate(getTurningPosition(), state.angle.getRadians()));
        // Debug:回傳State的狀態
        //SmartDashboard.putString("Swerve[" + absoluteEncoder.getChannel() + "] state", state.toString());
        SmartDashboard.putString("Swerve[" + absoluteEncoder.getDeviceID() + "] state", state.toString());
    
    }

    public void stop(){
        driveMotor.stopMotor();
        turningMotor.stopMotor();
    }

    public SwerveModulePosition getPosition(){
        return new SwerveModulePosition(
            driveMotor.getPosition().getValueAsDouble(),
            new Rotation2d(getTurningPosition())
        );
    }
}