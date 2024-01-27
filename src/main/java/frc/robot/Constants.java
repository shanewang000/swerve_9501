package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public final class Constants {
  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;

    public static final int kDriverYAxis = 1;
    public static final int kDriverXAxis = 0;
    public static final int kDriverRotAxis = 4;
    public static final int kDriverFieldOrientedButtonIdx = 1;

    public static final double kDeadband = 0.08;
  }

  public static class ModuleConstants{
    // 輪子轉向的kP常數
    public static final double kPTurning = 0.5;

    // 輪子單位換算
    public static final double kWheelDiameterMeters = 0.0992;
    public static final double kDriveMotorGearRatio = 1 / 5.95;
    public static final double kTurningMotorGearRatio = 1 / 21;

    public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters; // Position
    public static final double kDriveEncoderRot2MeterPerSec = kDriveEncoderRot2Meter / 60; // Velocity
    public static final double kTurningEncoderRot2Rad = (kTurningMotorGearRatio * 2 * Math.PI); // Position
    public static final double kTurningEncoderRot2RadPerSec = kTurningEncoderRot2Rad / 60; // Velocity
  }

  public static class DriveConstants{
    // 記得改
    public static final double Length = 25.984; // inch
    public static final double Width = 25.984;  // inch

    public static final double kPhysicalMaxSpeedMeterPerSecond = 5;

    // 四顆輪子的DriveMotorID
    public static final int kFrontLeftDriveMotorPort = 5;
    public static final int kBackLeftDriveMotorPort = 6;
    public static final int kFrontRightDriveMotorPort = 7;
    public static final int kBackRightDriveMotorPort = 8;

    // 四顆輪子的TurningMotorID
    public static final int kFrontLeftTurningMotorPort = 1;
    public static final int kBackLeftTurningMotorPort = 2;
    public static final int kFrontRightTurningMotorPort = 3;
    public static final int kBackRightTurningMotorPort = 4;

    // 四個輪子的TurningEncoder要不要反轉
    public static final boolean kFrontLeftTurningEncoderReversed = true;
    public static final boolean kBackLeftTurningEncoderReversed = true;
    public static final boolean kFrontRightTurningEncoderReversed = true;
    public static final boolean kBackRightTurningEncoderReversed = true;

    // 四顆輪子的DriveEncoder要不要反轉
    public static final boolean kFrontLeftDriveEncoderReversed = true;
    public static final boolean kBackLeftDriveEncoderReversed = true;
    public static final boolean kFrontRightDriveEncoderReversed = false;
    public static final boolean kBackRightDriveEncoderReversed = false;

    // 四顆輪子的AbsoluteEncoderPort
    public static final int kFrontLeftDriveAbsoluteEncoderPort = 9;
    public static final int kBackLeftDriveAbsoluteEncoderPort = 10;
    public static final int kFrontRightDriveAbsoluteEncoderPort = 11;
    public static final int kBackRightDriveAbsoluteEncoderPort = 12;

    // 四顆輪子的Encoder要不要反轉
    public static final boolean kFrontLeftAbsoluteEncoderReversed = false;
    public static final boolean kBackLeftAbsoluteEncoderReversed = false;
    public static final boolean kFrontRightAbsoluteEncoderReversed = false;
    public static final boolean kBackRightAbsoluteEncoderReversed = false;

    // 四顆輪子AbsoluteEncoder的Offset(要自己調參數)
   public static final double kFrontLeftAbsoluteEncoderOffsetRad = 3.06182565261974;
   public static final double kBackLeftAbsoluteEncoderOffsetRad = 0.06289321230331128;
   public static final double kFrontRightAbsoluteEncoderOffsetRad = 0.04601942363656923;
   public static final double kBackRightAbsoluteEncoderOffsetRad = 6.261709576149188;

    public static final SwerveDriveKinematics kSwerveKinematics = new SwerveDriveKinematics(
      new Translation2d(Length/2, Width/2),
      new Translation2d(Length/2, -Width/2),
      new Translation2d(-Length/2, Width/2),
      new Translation2d(-Length/2, -Width/2)
    );

    // 調底盤速度
    public static final double kPhysicalMaxSpeedMetersPerSecond = 5;
    
    public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

    public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 4 * 48.76;
    public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond / 4;
    public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
    public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
  }

  public static class ShooterConstants{
    public static final int kShooterMotorPort = 9;
  }

  public static class IntakeConstants{
    public static final int kIntakeMotorPort = 10;
    public static final int kIntakeCtrlMotorPort = 11;
  }
}